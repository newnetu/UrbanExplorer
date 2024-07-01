package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the UserData type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UserData", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class UserData implements Model {
  public static final QueryField ID = field("UserData", "id");
  public static final QueryField NAME = field("UserData", "name");
  public static final QueryField POINTS = field("UserData", "points");
  public static final QueryField EMAIL = field("UserData", "Email");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Int") Integer points;
  private final @ModelField(targetType="AWSEmail", isRequired = true) String Email;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Integer getPoints() {
      return points;
  }
  
  public String getEmail() {
      return Email;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private UserData(String id, String name, Integer points, String Email) {
    this.id = id;
    this.name = name;
    this.points = points;
    this.Email = Email;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UserData userData = (UserData) obj;
      return ObjectsCompat.equals(getId(), userData.getId()) &&
              ObjectsCompat.equals(getName(), userData.getName()) &&
              ObjectsCompat.equals(getPoints(), userData.getPoints()) &&
              ObjectsCompat.equals(getEmail(), userData.getEmail()) &&
              ObjectsCompat.equals(getCreatedAt(), userData.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), userData.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getPoints())
      .append(getEmail())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UserData {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("points=" + String.valueOf(getPoints()) + ", ")
      .append("Email=" + String.valueOf(getEmail()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static UserData justId(String id) {
    return new UserData(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      points,
      Email);
  }
  public interface NameStep {
    EmailStep name(String name);
  }
  

  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    UserData build();
    BuildStep id(String id);
    BuildStep points(Integer points);
  }
  

  public static class Builder implements NameStep, EmailStep, BuildStep {
    private String id;
    private String name;
    private String Email;
    private Integer points;
    public Builder() {
      
    }
    
    private Builder(String id, String name, Integer points, String Email) {
      this.id = id;
      this.name = name;
      this.points = points;
      this.Email = Email;
    }
    
    @Override
     public UserData build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UserData(
          id,
          name,
          points,
          Email);
    }
    
    @Override
     public EmailStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.Email = email;
        return this;
    }
    
    @Override
     public BuildStep points(Integer points) {
        this.points = points;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, Integer points, String email) {
      super(id, name, points, Email);
      Objects.requireNonNull(name);
      Objects.requireNonNull(Email);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder points(Integer points) {
      return (CopyOfBuilder) super.points(points);
    }
  }
  

  public static class UserDataIdentifier extends ModelIdentifier<UserData> {
    private static final long serialVersionUID = 1L;
    public UserDataIdentifier(String id) {
      super(id);
    }
  }
  
}
