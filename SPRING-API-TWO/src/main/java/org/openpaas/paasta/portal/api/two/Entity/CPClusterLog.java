package org.openpaas.paasta.portal.api.two.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity
 * 해당 클래스가 엔티티임을 알리기 위해 사용한다. 애플리케이션이 실행이 될 때 엔티티 자동검색을 통하여 이 어노테이션이 선언 된 클래스들은 엔티티 빈으로 등록한다.
 *
 * Table
 * 데이터 저장소인 테이블을 의미하며, name 값은 실제 데이터베이스의 테이블명이고, 생략하면 클레스의 이름을 테이블의 이름으로 자동 인식한다.
 */
@Entity
@Table(name = "cp_cluster_log")
public class CPClusterLog {

	/**
     * Id
     * 엔티티빈의 기본키를 의미하며, 하나의 엔티티에는 반드시 존재해야 한다.
     *
     * Column
     * 필드와 테이블의 컬럼을 매핑시켜준다. 생략이 가능하며, 생략시 필드의 이름이 테이블의 컬럼으로 자동으로 매핑이된다.
     */
    @Id
    @Column(name = "cluster_id", nullable = false)
    private String clusterId;

    @Column(name = "process_no", nullable = false)
    private String processNo;

    @Column(name = "log_message", nullable = false)
    private String logMessage;

   

    /**
     * CreationTimestamp
     * Insert 시 자동으로 시간을 입력해준다.
     *
     * Temporal
     * Date 를 DB 타입에 맞게 매핑할 수 있다.
     */
    @CreationTimestamp
    @Column(name = "reg_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regTimestamp;


    /**
     * Transient
     * DB 테이블에 간섭하지 않고, 엔티티 클래스 내부에서만 사용하는 필드.
     */
    @Transient
    private String searchKeyword;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getLogMessage() {
        return logMessage;
    }
    
    public void setLogMessage(String logMessage) {
    	this.logMessage = logMessage;
    }

    

    /**
     * JsonFormat
     * 날짜를 Json 포멧 형식으로 나타낸다.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    public Date getRegTimeStamp() {
        return regTimestamp;
    }

    public void setRegTimeStamp(Date regTimestamp) {
        this.regTimestamp = regTimestamp;
    }

    @Override
    public String toString() {
        return "CPClusterLog {" + "clusterId=" + clusterId + ", processNo='" + processNo + '\'' + ", logMessage='" + logMessage + '\'' + ", regTimestamp='" + regTimestamp + '\'' + '}';
    }
}
