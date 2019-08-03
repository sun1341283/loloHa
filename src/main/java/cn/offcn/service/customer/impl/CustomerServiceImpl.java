package cn.offcn.service.customer.impl;

import cn.offcn.cutpage.PageView;
import cn.offcn.entity.Customer;
import cn.offcn.entity.CustomerExample;
import cn.offcn.mapper.CustomerMapper;
import cn.offcn.service.customer.CustomerService;
import cn.offcn.utils.OAResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 添加客户
     * @param customer
     * @return
     */
    @Override
    public OAResult saveCustomer(Customer customer){
        //设置添加时间
        customer.setAddtime(new Date());
       int rows= customerMapper.insert(customer);
       if(rows==1){
           return OAResult.ok(200,"客户添加成功");
       }
       return OAResult.ok(400,"客户添加失败");
    }

    @Override
    public PageView<Customer> getCustomers(int currentPage){

        PageView<Customer> pageView=new PageView<>();
        pageView.setCurrentPage(currentPage);
        PageHelper.startPage(currentPage,pageView.getPageSize());
        CustomerExample customerExample=new CustomerExample();
        List<Customer> customerList=customerMapper.selectByExample(customerExample);
        PageInfo<Customer> pageInfo=new PageInfo<>(customerList);
        pageView.setTotalrecords(pageInfo.getTotal());
        pageView.setRecords(pageInfo.getList());
        return pageView;
    }

    @Override
    public List<Customer> getSearchCustomers(int searchContentType, String searchContent, int orderby){

        CustomerExample customerExample=new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();

        //默认为搜索客户所在公司名称
        if(searchContentType==0 || searchContentType==1){
             //搜索内容不为空
             if(StringUtils.isNotBlank(searchContent)){
                 criteria.andComnameLike("%"+searchContent+"%");
             }

        }else if (searchContentType==2){
            //按姓名搜索
            if(StringUtils.isNotBlank(searchContent)){
                criteria.andCompanypersonLike("%"+searchContent+"%");
            }
        }

        //按id的降序
        if(orderby==0){
            customerExample.setOrderByClause("id DESC");
        }else if(orderby==1){
            customerExample.setOrderByClause("addtime ASC");
        }

        return customerMapper.selectByExample(customerExample);
    }

    @Override
    public OAResult deleteCustomer(String ids) {
        final CustomerExample customerExample = new CustomerExample();
        final CustomerExample.Criteria criteria = customerExample.createCriteria();
        final String[] split = ids.split(",");
        System.out.println(split);
        criteria.andIdIn(split);
        final int i = customerMapper.deleteByExample(customerExample);
        if (i == 0 ){
            return OAResult.ok(400,"客户删除失败！");
        }else {
            return OAResult.ok(200,"客户删除成功！");
        }
    }
}
