package com.wehavescience.qualifiers;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Qualifier
@Retention(RUNTIME)
public @interface Template {
    @Nonbinding String mustacheFile();
}
