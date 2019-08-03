package cn.offcn.service.customer;

import cn.offcn.cutpage.PageView;
import cn.offcn.entity.Customer;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface CustomerService {

    /**
     * 添加客户
     * @param customer
     * @return
     */
    public OAResult saveCustomer(Customer customer);

    /**
     * 查询所有的客户
     * @return
     */
    public PageView<Customer> getCustomers(int currentPage);

    /**
     * 根据条件搜索客户
     * @param searchContentType
     * @param searchContent
     * @param orderb
     * @return
     */
    public List<Customer> getSearchCustomers(int searchContentType, String searchContent, int orderb);

    OAResult deleteCustomer(String ids);
}
