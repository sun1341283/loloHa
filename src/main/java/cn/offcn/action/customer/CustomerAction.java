package cn.offcn.action.customer;

import cn.offcn.cutpage.PageView;
import cn.offcn.entity.Customer;
import cn.offcn.service.customer.CustomerService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cus")
public class CustomerAction {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/forward/{page}")
    public String forwardCustomerPage(@PathVariable("page") String page){

        return "customer/"+page;
    }

    @ResponseBody
    @RequestMapping("/add")
    public OAResult addCustomer(Customer customer){
        return  customerService.saveCustomer(customer);
    }

    @ResponseBody
    @RequestMapping("/query")
    public PageView<Customer> getCustomers(@RequestParam(value="currentPage",defaultValue="1") int currentPage){

        return  customerService.getCustomers(currentPage);
    }

    @ResponseBody
    @RequestMapping("/seacher/query")
    public List<Customer> getSearchCustomers(int searchContentType,String searchContent,int orderby){

        return  customerService.getSearchCustomers(searchContentType,searchContent,orderby);
    }

    @RequestMapping("/delete")
    public OAResult deleteCustomer(HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        return customerService.deleteCustomer(id);

    }
}
