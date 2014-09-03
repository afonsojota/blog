package com.wehavescience.domain;

import lombok.Data;

import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Data
public class Rate {
    public List<Integer> rate;

    public Integer avg() {
        Integer total = 0;

        for (Integer r : rate) {
            total = total + r;
        }

        return total;
    }
}
