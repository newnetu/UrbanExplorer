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

/** This is an auto generated class representing the LocationInfo type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "LocationInfos", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class LocationInfo implements Model {
  public static final QueryField ID = field("LocationInfo", "id");
  public static final QueryField CATEGORY = field("LocationInfo", "Category");
  public static final QueryField IMAGE_KEY = field("LocationInfo", "ImageKey");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Preferences") Preferences Category;
  private final @ModelField(targetType="String") String ImageKey;
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
  
  public Preferences getCategory() {
      return Category;
  }
  
  public String getImageKey() {
      return ImageKey;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private LocationInfo(String id, Preferences Category, String ImageKey) {
    this.id = id;
    this.Category = Category;
    this.ImageKey = ImageKey;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      LocationInfo locationInfo = (LocationInfo) obj;
      return ObjectsCompat.equals(getId(), locationInfo.getId()) &&
              ObjectsCompat.equals(getCategory(), locationInfo.getCategory()) &&
              ObjectsCompat.equals(getImageKey(), locationInfo.getImageKey()) &&
              ObjectsCompat.equals(getCreatedAt(), locationInfo.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), locationInfo.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCategory())
      .append(getImageKey())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("LocationInfo {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Category=" + String.valueOf(getCategory()) + ", ")
      .append("ImageKey=" + String.valueOf(getImageKey()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static LocationInfo justId(String id) {
    return new LocationInfo(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Category,
      ImageKey);
  }
  public interface BuildStep {
    LocationInfo build();
    BuildStep id(String id);
    BuildStep category(Preferences category);
    BuildStep imageKey(String imageKey);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Preferences Category;
    private String ImageKey;
    public Builder() {
      
    }
    
    private Builder(String id, Preferences Category, String ImageKey) {
      this.id = id;
      this.Category = Category;
      this.ImageKey = ImageKey;
    }
    
    @Override
     public LocationInfo build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new LocationInfo(
          id,
          Category,
          ImageKey);
    }
    
    @Override
     public BuildStep category(Preferences category) {
        this.Category = category;
        return this;
    }
    
    @Override
     public BuildStep imageKey(String imageKey) {
        this.ImageKey = imageKey;
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
    private CopyOfBuilder(String id, Preferences category, String imageKey) {
      super(id, Category, ImageKey);
      
    }
    
    @Override
     public CopyOfBuilder category(Preferences category) {
      return (CopyOfBuilder) super.category(category);
    }
    
    @Override
     public CopyOfBuilder imageKey(String imageKey) {
      return (CopyOfBuilder) super.imageKey(imageKey);
    }
  }
  

  public static class LocationInfoIdentifier extends ModelIdentifier<LocationInfo> {
    private static final long serialVersionUID = 1L;
    public LocationInfoIdentifier(String id) {
      super(id);
    }
  }
  
}
