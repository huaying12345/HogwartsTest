import org.apache.commons.lang3.StringUtils;

public enum TokenAccountEnum {

    TIAN_GU("tiangu","7fb97b30-d48d-4dd8-9693-38c57b90024c","",""),
    SHAO_XING("sxjgjz20150810","826f98d2-6eb9-42ff-85cc-ae367e3b48a2","",""),
    LYH_3173("lyh3173","8c9ba9cb-3aef-4962-8213-07396078401d","","0"),
    LLH_CSQ("llhcsqy123","fe242b4e-4d98-404e-baa0-2912b06a55f9","","0"),
    QIUZHU("樊丽鹃","68bbcc22-b145-4914-8809-fae39856e46a","GE_2d41d9e698ff4b3b9c4cbad77269f9a3","76450408"),
    HUAYING("金婵媛","7077e1db-4bf9-47b9-8894-5603eb48a2cf","GE_d24b9e1578c74d658eb295eb6683bf30","76450364"),
    WEIPENG("魏鹏","22e2b170-b89a-4c9c-ad91-126b8628c1b7","GE_ba8a5088d101407690c42ecac845baaa","76450598"),
    YUNQIAN("吴新文","40e85a22-e9b2-4e67-9909-9df2dae37ded","GE_fd768c8fe14d445ba599e5bd8ddbf13e","639923"),
    ALIVE("泽言","b7fb7668-d059-44e8-bb7b-9a71f9a1723e","GE_92c83ab876154d05875e741423d8f3e7","5688533");

    private String name;
    private String accountUUId;
    private String moziUserCode;
    private String dingAccountId;

    TokenAccountEnum(String name, String accountUUId, String moziUserCode, String dingAccountId) {
        this.name = name;
        this.accountUUId = accountUUId;
        this.moziUserCode = moziUserCode;
        this.dingAccountId = dingAccountId;
    }

    /**
     * 根据value查找code
     */
    public static String getAcccountUUId(String name) {
        if (StringUtils.isBlank( name )) {
            return null;
        }
        for (TokenAccountEnum names : TokenAccountEnum.values()) {
            if (names.name.equals( name )) {
                return names.accountUUId;
            }

        }
        return null;
    }

    /**
     * 根据value查找code
     */
    public static String getMoziUserCode(String name) {
        if (StringUtils.isBlank( name )) {
            return null;
        }
        for (TokenAccountEnum names : TokenAccountEnum.values()) {
            if (names.name.equals( name )) {
                return names.moziUserCode;
            }

        }
        return null;
    }

    /**
     * 根据value查找code
     */
    public static String getDingaccountid(String name) {
        if (StringUtils.isBlank( name )) {
            return null;
        }
        for (TokenAccountEnum names : TokenAccountEnum.values()) {
            if (names.name.equals( name )) {
                return names.dingAccountId;
            }

        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountUUId() {
        return accountUUId;
    }

    public void setAccountUUId(String accountUUId) {
        this.accountUUId = accountUUId;
    }
}
