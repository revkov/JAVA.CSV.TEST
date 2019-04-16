package com.brekhin.smartSoft.to.out;

import com.brekhin.smartSoft.repository.projection.IFrequentlyUsedFormsTOProjection;

import java.math.BigInteger;

public class FrequentlyUsedFormsTO implements IFrequentlyUsedFormsTOProjection {

    private final String formid;
    private final BigInteger count;

    public FrequentlyUsedFormsTO(String formid, BigInteger count) {
        this.formid = formid;
        this.count = count;
    }

    public String getFormid() {
        return formid;
    }

    public BigInteger getCount() {
        return count;
    }
}
