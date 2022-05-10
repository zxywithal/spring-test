package org.think.in.spring.ioc.overview.domain;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;
import org.think.in.spring.ioc.overview.enums.City;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

public class User implements BeanNameAware {
    private Long id;
    private String name;
    private City city;
    private Resource configFileLocation;

    private City[] workCities;

    private List<City> lifeCities;

    /**
     * 当前beanName
     */
    private transient String beanName;

    private Company company;

    private Properties context;

    public String getBeanName() {
        return beanName;
    }

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("city=" + city)
                .add("configFileLocation=" + configFileLocation)
                .add("workCities=" + Arrays.toString(workCities))
                .add("lifeCities=" + lifeCities)
                .add("company=" + company)
                .add("context=" + context)
                .toString();
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("zxy");
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println(beanName + "开始初始化");
    }

    @PreDestroy
    public void destory() {
        System.out.println(beanName + "开始销毁");
    }
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
