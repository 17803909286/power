package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2019-12-09
 */
public class UserSignatureBean extends BaseEntity {


    /**
     * signatures : [{"signatureTime":"2019-12-09","isSignature":true},{"signatureTime":"2019-12-10","isSignature":false},{"signatureTime":"2019-12-11","isSignature":false},{"signatureTime":"2019-12-12","isSignature":false},{"signatureTime":"2019-12-13","isSignature":false},{"signatureTime":"2019-12-14","isSignature":false},{"signatureTime":"2019-12-15","isSignature":false}]
     * continueSignatureCount : 1
     */

    private int continueSignatureCount;
    private List<SignaturesBean> signatures;

    public int getContinueSignatureCount() {
        return continueSignatureCount;
    }

    public void setContinueSignatureCount(int continueSignatureCount) {
        this.continueSignatureCount = continueSignatureCount;
    }

    public List<SignaturesBean> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<SignaturesBean> signatures) {
        this.signatures = signatures;
    }

    public static class SignaturesBean extends BaseEntity {
        /**
         * signatureTime : 2019-12-09
         * isSignature : true
         */

        private String signatureTime;
        private boolean isSignature;

        public String getSignatureTime() {
            return signatureTime;
        }

        public void setSignatureTime(String signatureTime) {
            this.signatureTime = signatureTime;
        }

        public boolean isIsSignature() {
            return isSignature;
        }

        public void setIsSignature(boolean isSignature) {
            this.isSignature = isSignature;
        }
    }
}
