package com.project3.couponSystem.clr;

import com.project3.couponSystem.Repository.CompanyRepository;
import com.project3.couponSystem.Repository.CouponRepository;
import com.project3.couponSystem.Repository.CustomerRepository;
import com.project3.couponSystem.beans.Category;
import com.project3.couponSystem.beans.Company;
import com.project3.couponSystem.beans.Coupon;
import com.project3.couponSystem.beans.Customer;
import com.project3.couponSystem.exceptions.CouponSystemException;
import com.project3.couponSystem.front.ClientType;
import com.project3.couponSystem.front.LoginManager;
import com.project3.couponSystem.services.AdminService;
import com.project3.couponSystem.services.CompanyService;
import com.project3.couponSystem.services.CustomerService;
import com.project3.couponSystem.utils.ConsoleColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println(ConsoleColors.GREEN_BOLD + "" +
                    "   #                                                                          \n" +
                    "  # #   #####  #    # # #    #     ####  ###### #####  #    # #  ####  ###### \n" +
                    " #   #  #    # ##  ## # ##   #    #      #      #    # #    # # #    # #      \n" +
                    "#     # #    # # ## # # # #  #     ####  #####  #    # #    # # #      #####  \n" +
                    "####### #    # #    # # #  # #         # #      #####  #    # # #      #      \n" +
                    "#     # #    # #    # # #   ##    #    # #      #   #   #  #  # #    # #      \n" +
                    "#     # #####  #    # # #    #     ####  ###### #    #   ##   #  ####  ###### " + ConsoleColors.RESET);


            AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
            Company company1 = Company.builder().name("company1").email("company1@company.com").password("1234").build();
            Company company2 = Company.builder().name("company2").email("company2@company.com").password("1235").build();
            Company company3 = Company.builder().name("company3").email("company3@company.com").password("1236").build();
            Company company4 = Company.builder().name("company4").email("company4@company.com").password("1237").build();
            System.out.println(ConsoleColors.RED_BOLD + "-------------add all companies -----------" + ConsoleColors.RESET);
            adminService.addCompany(company1);
            adminService.addCompany(company2);
            adminService.addCompany(company3);
            adminService.addCompany(company4);
            System.out.println(ConsoleColors.BLUE_BOLD + "Companies  was added successfully" + ConsoleColors.RESET);

            try {
                company1.setId(1);
                adminService.addCompany(company1);
            } catch (CouponSystemException e) {
                System.out.println(e.getMessage() + "-> check 1 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "-------------update company -----------" + ConsoleColors.RESET);
            Company company = adminService.getSingleCompany(1);
            company.setEmail("elishay61@gmail.com");
            adminService.updateCompany(company);
            System.out.println(ConsoleColors.BLUE_BOLD + "Company was updated successfully" + ConsoleColors.RESET);
            try {
                company.setName("zara");
                adminService.updateCompany(company);
            } catch (CouponSystemException e) {
                System.out.println(e.getMessage() + "-> check 2 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "-------------delete company -----------" + ConsoleColors.RESET);
            adminService.deleteCompany(1);
            System.out.println(ConsoleColors.BLUE_BOLD + "Company was deleted successfully" + ConsoleColors.RESET);
            try {
                adminService.deleteCompany(1);
            } catch (CouponSystemException e) {
                System.out.println(e.getMessage() + "-> check 3 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "get all companies " + ConsoleColors.RESET);
            adminService.getAllCompanies().forEach(System.out::println);
            System.out.println(ConsoleColors.RED_BOLD + "---------getting company by id-----------------" + ConsoleColors.RESET);
            System.out.println(adminService.getSingleCompany(2));
            try {
                System.out.println(adminService.getSingleCompany(1));
            } catch (CouponSystemException e) {
                System.out.println(e.getMessage() + "-> check 4 is working");
            }

            System.out.println(ConsoleColors.RED_BOLD + "---------add customers-----------------" + ConsoleColors.RESET);
            adminService.addCustomer(Customer.builder().firstName("customer1").last_Name("cohen").email("customer1@gmail.com").password("1234").build());
            adminService.addCustomer(Customer.builder().firstName("customer2").last_Name("levi").email("customer2@gmail.com").password("1234").build());
            adminService.addCustomer(Customer.builder().firstName("customer3").last_Name("amar").email("customer3@gmail.com").password("1234").build());
            adminService.addCustomer(Customer.builder().firstName("customer4").last_Name("shitrit").email("customer4@gmail.com").password("1234").build());
            System.out.println(ConsoleColors.BLUE_BOLD + " Customers successfully added:" + ConsoleColors.RESET);
            try {
                adminService.addCustomer(Customer.builder().firstName("customer1").last_Name("cohen").email("customer1@gmail.com").password("1234").build());
            } catch (CouponSystemException e) {
                System.out.println(e.getMessage() + "-> check 5 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "---------------update customer name------------------" + ConsoleColors.RESET);
            Customer customer = adminService.getCustomer(1);
            customer.setFirstName("customer1 -> updated");
            adminService.updateCustomer(customer);
            System.out.println(ConsoleColors.BLUE_BOLD + "customer was updated successfully" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "--------------get all customers------------------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            adminService.getCustomers().forEach(System.out::println);
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "--------------get one customer-----------------" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + adminService.getCustomer(2) + ConsoleColors.RESET);
            try {
                adminService.getCustomer(7);
            } catch (CouponSystemException e) {
                System.out.println(e.getMessage() + "-> check 6 is working");
            }
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage() + "-> check  is not working");
        }
        try {
            AdminService adminService = (AdminService) loginManager.login("adminush@admin.com", "admin", ClientType.Administrator);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "-> check login error for admin facade is working");
        }
        try {
            System.out.println(ConsoleColors.GREEN_BOLD + "" +
                    " #####                                                                                          \n" +
                    "#     #  ####  #    # #####    ##   #    # #   #     ####  ###### #####  #    # #  ####  ###### \n" +
                    "#       #    # ##  ## #    #  #  #  ##   #  # #     #      #      #    # #    # # #    # #      \n" +
                    "#       #    # # ## # #    # #    # # #  #   #       ####  #####  #    # #    # # #      #####  \n" +
                    "#       #    # #    # #####  ###### #  # #   #           # #      #####  #    # # #      #      \n" +
                    "#     # #    # #    # #      #    # #   ##   #      #    # #      #   #   #  #  # #    # #      \n" +
                    " #####   ####  #    # #      #    # #    #   #       ####  ###### #    #   ##   #  ####  ###### \n" +
                    "                                                                                                " + ConsoleColors.RESET);
            CompanyService companyService = (CompanyService) loginManager.login("company2@company.com", "1235", ClientType.Company);
            Company company = Company.builder().coupons(new ArrayList<>()).coupons(new ArrayList<>()).build();
            System.out.println(ConsoleColors.RED_BOLD + "-------------add all coupons -----------" + ConsoleColors.RESET);
            Date start = java.sql.Date.valueOf(LocalDate.now().plusDays(7));
            Date end = java.sql.Date.valueOf(LocalDate.now().plusDays(21));
            Date startEx = Date.valueOf(LocalDate.of(2021, 12, 12));
            Date endEx = Date.valueOf(LocalDate.now().minusDays(1));
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.ENTERTAINMENT)
                    .title("movie for free")
                    .description("free movie")
                    .startDate(start)
                    .endDate(end).amount(100).price(50.5).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.ENTERTAINMENT)
                    .title("bowling for free")
                    .description("free bowling")
                    .startDate(start)
                    .endDate(end).amount(100).price(70).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.READING)
                    .title("book for free")
                    .description("free book of Harry Potter")
                    .startDate(start)
                    .endDate(end).amount(100).price(98).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.ELECTRICITY)
                    .title("lamp for free")
                    .description("free lamp")
                    .startDate(start)
                    .endDate(end).amount(100).price(2).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.FOOD)
                    .title("bananas for free")
                    .description("free bananas for life")
                    .startDate(start)
                    .endDate(end).amount(100).price(500).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.READING)
                    .title("free bible book")
                    .description("free bible book")
                    .startDate(start)
                    .endDate(end).amount(100).price(98).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.RESTAURANT)
                    .title("free meal")
                    .description("free meal at cafecafe")
                    .startDate(start)
                    .endDate(end).amount(100).price(98).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.VACATION)
                    .title("10% at Dan hotel")
                    .description("10% at Dan hotel")
                    .startDate(start)
                    .endDate(end).amount(100).price(0).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.ELECTRICITY)
                    .title("TV for free")
                    .description("free TV")
                    .startDate(start)
                    .endDate(end).amount(100).price(98).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.READING)
                    .title("free book of Harry Potter 2")
                    .description("free book of Harry Potter 2")
                    .startDate(start)
                    .endDate(end).amount(100).price(98).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.READING)
                    .title("free book of Harry Potter 3")
                    .description("free book of Harry Potter 3")
                    .startDate(start)
                    .endDate(end).amount(0).price(98).image("l").build());
            companyService.addCoupon(2, Coupon.builder()
                    .category(Category.READING)
                    .title("free book of Harry Potter 4")
                    .description("free book of Harry Potter 4")
                    .startDate(start)
                    .endDate(end).amount(1).price(98).image("l").build());
            System.out.println(ConsoleColors.BLUE_BOLD + "Coupons  was added successfully " + ConsoleColors.RESET);
            try {
                companyService.addCoupon(2, companyService.getCoupons(2).get(0));
            } catch (Exception e) {
                System.out.println(e.getMessage() + "-> check 7 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "-------------update coupon  -----------" + ConsoleColors.RESET);
            Coupon coupon = companyService.getCoupons(2).get(0);
            coupon.setAmount(75);
            companyService.updateCoupon(2, coupon.getId(), coupon);
            System.out.println(ConsoleColors.BLUE_BOLD + "Coupons  was updated successfully" + ConsoleColors.RESET);
            try {
                Coupon coupon1 = companyService.getCoupons(2).get(0);
                coupon1.getCompany().setId(20);
                companyService.updateCoupon(2, coupon1.getId(), coupon1);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "-> check 8 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "-------------delete coupon  -----------" + ConsoleColors.RESET);
            companyService.deleteCoupon(2, coupon.getId());
            System.out.println(ConsoleColors.BLUE_BOLD + "coupon was deleted successfully " + ConsoleColors.RESET);
            try {
                companyService.deleteCoupon(2, 80);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "-> check 9 is working");
            }
            System.out.println(ConsoleColors.RED_BOLD + "-------------get all coupons  -----------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            companyService.getCoupons(2).forEach(System.out::println);
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "-------------get all coupons by a specific category  -----------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            companyService.getCouponsByCategory(2, Category.READING).forEach(System.out::println);
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "-------------get all coupons in the max range -----------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            companyService.getCouponsByMaxPrice(2, 85).forEach(System.out::println);
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "-------------display the company details -----------" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + companyService.getDetails(2) + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "-> check  is not working");
        }
        try {
            CompanyService companyService1 = (CompanyService) loginManager.login("kk@gmail.com", "1234", ClientType.Company);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "-> check login error for company facade is working");
        }
        try {
            System.out.println(ConsoleColors.GREEN_BOLD + "" +
                    " #####                                                      #####                                       \n" +
                    "#     # #    #  ####  #####  ####  #    # ###### #####     #     # ###### #####  #    # #  ####  ###### \n" +
                    "#       #    # #        #   #    # ##  ## #      #    #    #       #      #    # #    # # #    # #      \n" +
                    "#       #    #  ####    #   #    # # ## # #####  #    #     #####  #####  #    # #    # # #      #####  \n" +
                    "#       #    #      #   #   #    # #    # #      #####           # #      #####  #    # # #      #      \n" +
                    "#     # #    # #    #   #   #    # #    # #      #   #     #     # #      #   #   #  #  # #    # #      \n" +
                    " #####   ####   ####    #    ####  #    # ###### #    #     #####  ###### #    #   ##   #  ####  ###### "
                    + ConsoleColors.RESET);
            CustomerService customerService = (CustomerService) loginManager.login("customer4@gmail.com", "1234", ClientType.Customer);
            CompanyService companyService = (CompanyService) loginManager.login("company2@company.com", "1235", ClientType.Company);
            System.out.println(ConsoleColors.RED_BOLD + "-------------buy coupons -----------" + ConsoleColors.RESET);
            int id=(companyService.getCoupons(2).get(0).getId());
            customerService.purchaseCoupon(1,id);
            customerService.purchaseCoupon(1,(companyService.getCoupons(2).get(1).getId()));
            customerService.purchaseCoupon(1,(companyService.getCoupons(2).get(2).getId()));
            System.out.println(ConsoleColors.BLUE_BOLD + "Coupons  was purchased successfully " + ConsoleColors.RESET);
            try {
                Coupon coupon = companyService.getCoupons(2).get(0);
                customerService.purchaseCoupon(1,coupon.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage() + "-> check 10 is working!");
            }
            try {
                Coupon coupon = companyService.getCoupons(2).get(companyService.getCoupons(2).size()-2);
                customerService.purchaseCoupon(1,coupon.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage() + "-> check 11 is working!");
            }
            try {
                Coupon coupon = companyService.getCoupons(2).get(companyService.getCoupons(2).size()-1);
                customerService.purchaseCoupon(1,coupon.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage() + "-> check 12 is working!");
            }
            companyService.deleteCoupon(2,3);
            System.out.println(ConsoleColors.RED_BOLD + "-------------get all coupons purchased  -----------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            customerService.getCustomersCoupons(1).forEach(System.out::println);
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "-------------get all coupons by a specific category  -----------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            customerService.getCouponsByCategory(1,Category.ENTERTAINMENT).forEach(System.out::println);
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "-------------get all coupons in the max range -----------" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.BLUE_BOLD);
            System.out.println(customerService.getCustomerCouponsByMaxPrice(1,1000.0));
            System.out.print(ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "-------------display the customer details -----------" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + customerService.getDetails(1) + ConsoleColors.RESET);

        }catch (Exception e) {
            System.out.println(e.getMessage() + "-> check  is not working");
        }
        try {
            CustomerService customerService= (CustomerService) loginManager.login("kk@gmail.com","1234",ClientType.Customer);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "-> check login error for customer facade is working");
        }
    }
}
