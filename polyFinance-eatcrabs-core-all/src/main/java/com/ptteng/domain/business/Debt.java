package com.ptteng.domain.business;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "b_debt", indexes = {
        @Index(name = "uk_number", columnList = "number", unique = true)})//债权信息录表
public class Debt implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    /**
     * 债权人姓名
     */
    @Column(name = "name", columnDefinition = "char(5)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权人姓名非空")
    @Size(min = 1, max = 5, message = "债权人姓名", groups = VoGroup.class)
    private String name; //债权人姓名

    /**
     * 债权人身份证号
     */
    @Column(name = "id_card", columnDefinition = "char(18)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权人身份证号非空")
    @Pattern(regexp = "\\d{18}|\\d{15}", message = "债权人身份证号非法", groups = VoGroup.class)
    private String idCard;

    /**
     * 债权人手机号
     */
    @Column(name = "mobile", columnDefinition = "char(11)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权人手机号非空")
    @Pattern(regexp = "^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "非法的手机号", groups = VoGroup.class)
    private String mobile;

    /**
     * 债权人代号
     */
    @Column(name = "number", columnDefinition = "char(5)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权人代号非空")
    @Size(min = 5, max = 5, message = "债权人代号不正确",groups = VoGroup.class)
    private String number;

    /**
     * 年化利率
     */
    @Column(name = "rate", nullable = false)
    @NotNull(groups = VoGroup.class, message = "年化利率非空")
    @Range(min = 1, max = 20,groups = VoGroup.class)
    private Integer rate;

    /**
     * 债务金额
     */
    @Column(name = "amount", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债务金额非空")
    private Long amount;

    /**
     * 债权描述
     */
    @Column(name = "content", columnDefinition = "varchar(100)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权描述非空")
    @Size(min = 1, max = 100, message = "债权描述",groups = VoGroup.class)
    private String content;

    /**
     * 起息时间
     */
    @Column(name = "start", nullable = false)
    @NotNull(groups = VoGroup.class, message = "起息时间非空")
    private Long start;

    /**
     * 结束时间
     */
    @Column(name = "finish", nullable = false)
    @NotNull(groups = VoGroup.class, message = "结束时间非空")
    private Long finish;

    /**
     * 债权性质
     */
    @Column(name = "kind", columnDefinition = "char(10)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权性质非空")
    @Size(min = 1, max = 10,groups = VoGroup.class)
    private String kind;  //债权性质

    /**
     * 相关备注
     */
    @Column(name = "tip", columnDefinition = "char(10)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "相关备注非空")
    @Size(min = 1, max = 10,groups = VoGroup.class)
    private String tip;

    /**
     * 附件url
     */
    @Column(name = "doc", columnDefinition = "varchar(150)")
    private String doc;

    /**
     * 已匹配金额
     */
    @Column(name = "matched", nullable = false)
    @Null(groups = VoGroup.class, message = "已匹配金额非法")
    private Long match;

    /**
     * 期限
     */
    @Column(name = "term", nullable = false)
    @Null(groups = VoGroup.class, message = "期限非法")
    private Long term;

    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class, message = "创建时间非法")
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    @Null(groups = VoGroup.class, message = "更新时间非法")
    private Long updateAt; //更新时间

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", mobile='" + mobile + '\'' +
                ", number='" + number + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                ", match=" + match +
                ", content='" + content + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", term=" + term +
                ", kind='" + kind + '\'' +
                ", tip='" + tip + '\'' +
                ", doc='" + doc + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getMatch() {
        return match;
    }

    public void setMatch(Long match) {
        this.match = match;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getFinish() {
        return finish;
    }

    public void setFinish(Long finish) {
        this.finish = finish;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }
}
