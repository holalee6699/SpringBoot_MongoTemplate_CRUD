package com.holalee.mongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("employee")
public class Employee extends MongoBean{


}
