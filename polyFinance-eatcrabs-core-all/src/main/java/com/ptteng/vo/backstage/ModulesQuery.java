package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class ModulesQuery extends BaseQuery implements Serializable {
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "name")
    private String moduleName;
    @AutoQuery
    private String fmoduleId;

    @Override
    public String toString() {
        return "ModulesQuery{" +
                "moduleName='" + moduleName + '\'' +
                ", fmoduleId='" + fmoduleId + '\'' +
                '}';
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFmoduleId() {
        return fmoduleId;
    }

    public void setFmoduleId(String fmoduleId) {
        this.fmoduleId = fmoduleId;
    }
}
