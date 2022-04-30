package org.think.in.spring.ioc.overview.domain;

import org.think.in.spring.ioc.overview.annotation.Super;

import java.util.StringJoiner;

@Super
public class SuperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SuperUser.class.getSimpleName() + "[", "]")
                .add("address='" + address + "'")
                .add(super.toString())
                .toString();
    }
}
