package io.srikanth.ProfileManager.Codeforces;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "contest")
public class Contest {

    @Id
    @PrimaryKeyColumn(name = "contest_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column("contest_name")
    @CassandraType(type = Name.TEXT)
    private String name;

    @Column("contest_type")
    @CassandraType(type = Name.TEXT)
    private ContestType type;

    public Contest(String id, String name) {
	super();
	this.id = id;
	this.name = name;
    }

    public Contest() {

    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public ContestType getType() {
	return type;
    }

    public void setType(ContestType type) {
	this.type = type;
    }

    @Override
    public String toString() {
	return "Contest [id=" + id + ", name=" + name + ", type=" + type + "]";
    }

}
