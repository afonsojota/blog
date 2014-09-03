package com.wehavescience.producers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.wehavescience.qualifiers.Template;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public class MustacheProducer {

    @Produces
    @Template(mustacheFile = "")
    private Mustache template(InjectionPoint injectionPoint){
        MustacheFactory mf = new DefaultMustacheFactory();
        return mf.compile(injectionPoint.getAnnotated().getAnnotation(Template.class).mustacheFile());
    }
}
