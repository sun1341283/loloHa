import com.youlexuan.pojo.TbItem;
import com.youlexuan.pojo.TbItemExample;
import mapper.TbItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring/applicationContext-redis.xml")
public class TestSolrTemplate {

    @Autowired(required = false)
    private SolrTemplate solrTemplate;
    @Autowired(required = false)
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private TbItemMapper itemMapper;


    @Test
    public void mysqlToRedis(){
        System.out.println(1);
        TbItemExample example = new TbItemExample();
        example.createCriteria().andTitleLike("%三星%");
        List<TbItem> itemList = itemMapper.selectByExample(example);
        System.out.println(itemList.size());
        for (TbItem item:itemList){
            redisTemplate.boundHashOps("item").put(item.getId(),item);
            System.out.println(item.getId());
        }
    }

    @Test
    public void testAdd(){
        /*ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext-solr.xml");
        SolrTemplate solrTemplate = (SolrTemplate) context.getBean("solrTemplate");*/
        TbItem item = new TbItem();
        item.setId(1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate9");
        item.setPrice(new BigDecimal(3000));
        solrTemplate.saveBean(item);
        solrTemplate.commit();

    }

    @Test
    public void testAddList(){
        List<TbItem> list=new ArrayList();

        for(int i=0;i<100;i++){
            TbItem item=new TbItem();
            item.setId(i+1L);
            item.setBrand("华为");
            item.setCategory("手机");
            item.setGoodsId(1L);
            item.setSeller("华为2号专卖店");
            item.setTitle("华为Mate"+i);
            item.setPrice(new BigDecimal(2000+i));
            list.add(item);
        }
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    @Test
    public void testPageQuery(){
        Query query=new SimpleQuery("*:*");
        query.setOffset(20);//开始索引（默认0）
        query.setRows(20);//每页记录数(默认10)
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数："+page.getTotalElements());
        List<TbItem> list = page.getContent();
        showList(list);
    }
    //显示记录数据
    private void showList(List<TbItem> list){
        for(TbItem item:list){
            System.out.println(item.getTitle() +item.getPrice());
        }
    }

    @Test
    public void testPageQueryMutil(){
        Query query=new SimpleQuery("*:*");
        /*Criteria criteria=new Criteria("item_title").contains("2");
        criteria=criteria.and("item_title").contains("5");*/
        Criteria criteria=new Criteria("item_keywords").is("三星");
        query.addCriteria(criteria);
        //query.setOffset(20);//开始索引（默认0）
        //query.setRows(20);//每页记录数(默认10)
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数："+page.getTotalElements());
        List<TbItem> list = page.getContent();
        showList(list);
    }

    @Test
    public void dele(){
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }


}
