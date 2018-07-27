package br.com.zup.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.zup.restful.ModelItemRS;
import br.com.zup.restful.ModelRS;

@ApplicationPath("/rest")
public class RestEasyServices extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public RestEasyServices() {
        singletons.add(new ModelRS());
        singletons.add(new ModelItemRS());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}