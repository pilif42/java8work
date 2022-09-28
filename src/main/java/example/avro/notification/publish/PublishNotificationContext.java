/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package example.avro.notification.publish;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class PublishNotificationContext extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5206048522894459337L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PublishNotificationContext\",\"namespace\":\"example.avro.notification.publish\",\"fields\":[{\"name\":\"version\",\"type\":\"string\",\"default\":\"1.0.0\"},{\"name\":\"publishTime\",\"type\":\"string\"},{\"name\":\"notification\",\"type\":{\"type\":\"record\",\"name\":\"NotificationContext\",\"fields\":[{\"name\":\"notifications\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Notification\",\"fields\":[{\"name\":\"notificationType\",\"type\":{\"type\":\"enum\",\"name\":\"NotificationType\",\"symbols\":[\"error\",\"warning\",\"information\",\"unclassified\"],\"default\":\"unclassified\"}},{\"name\":\"code\",\"type\":\"string\",\"default\":\"0\"},{\"name\":\"description\",\"type\":\"string\"}]}}}]}},{\"name\":\"event\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"EventContext\",\"namespace\":\"example.avro.event.publish\",\"fields\":[{\"name\":\"id\",\"type\":[\"null\",\"string\"]},{\"name\":\"eventType\",\"type\":[\"null\",\"string\"]}]}]},{\"name\":\"metadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"string\"}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PublishNotificationContext> ENCODER =
      new BinaryMessageEncoder<PublishNotificationContext>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PublishNotificationContext> DECODER =
      new BinaryMessageDecoder<PublishNotificationContext>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<PublishNotificationContext> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<PublishNotificationContext> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PublishNotificationContext>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this PublishNotificationContext to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a PublishNotificationContext from a ByteBuffer. */
  public static PublishNotificationContext fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence version;
  @Deprecated public java.lang.CharSequence publishTime;
  @Deprecated public example.avro.notification.publish.NotificationContext notification;
  @Deprecated public example.avro.event.publish.EventContext event;
  @Deprecated public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> metadata;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PublishNotificationContext() {}

  /**
   * All-args constructor.
   * @param version The new value for version
   * @param publishTime The new value for publishTime
   * @param notification The new value for notification
   * @param event The new value for event
   * @param metadata The new value for metadata
   */
  public PublishNotificationContext(java.lang.CharSequence version, java.lang.CharSequence publishTime, example.avro.notification.publish.NotificationContext notification, example.avro.event.publish.EventContext event, java.util.Map<java.lang.CharSequence,java.lang.CharSequence> metadata) {
    this.version = version;
    this.publishTime = publishTime;
    this.notification = notification;
    this.event = event;
    this.metadata = metadata;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return version;
    case 1: return publishTime;
    case 2: return notification;
    case 3: return event;
    case 4: return metadata;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: version = (java.lang.CharSequence)value$; break;
    case 1: publishTime = (java.lang.CharSequence)value$; break;
    case 2: notification = (example.avro.notification.publish.NotificationContext)value$; break;
    case 3: event = (example.avro.event.publish.EventContext)value$; break;
    case 4: metadata = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'version' field.
   * @return The value of the 'version' field.
   */
  public java.lang.CharSequence getVersion() {
    return version;
  }

  /**
   * Sets the value of the 'version' field.
   * @param value the value to set.
   */
  public void setVersion(java.lang.CharSequence value) {
    this.version = value;
  }

  /**
   * Gets the value of the 'publishTime' field.
   * @return The value of the 'publishTime' field.
   */
  public java.lang.CharSequence getPublishTime() {
    return publishTime;
  }

  /**
   * Sets the value of the 'publishTime' field.
   * @param value the value to set.
   */
  public void setPublishTime(java.lang.CharSequence value) {
    this.publishTime = value;
  }

  /**
   * Gets the value of the 'notification' field.
   * @return The value of the 'notification' field.
   */
  public example.avro.notification.publish.NotificationContext getNotification() {
    return notification;
  }

  /**
   * Sets the value of the 'notification' field.
   * @param value the value to set.
   */
  public void setNotification(example.avro.notification.publish.NotificationContext value) {
    this.notification = value;
  }

  /**
   * Gets the value of the 'event' field.
   * @return The value of the 'event' field.
   */
  public example.avro.event.publish.EventContext getEvent() {
    return event;
  }

  /**
   * Sets the value of the 'event' field.
   * @param value the value to set.
   */
  public void setEvent(example.avro.event.publish.EventContext value) {
    this.event = value;
  }

  /**
   * Gets the value of the 'metadata' field.
   * @return The value of the 'metadata' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMetadata() {
    return metadata;
  }

  /**
   * Sets the value of the 'metadata' field.
   * @param value the value to set.
   */
  public void setMetadata(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.metadata = value;
  }

  /**
   * Creates a new PublishNotificationContext RecordBuilder.
   * @return A new PublishNotificationContext RecordBuilder
   */
  public static example.avro.notification.publish.PublishNotificationContext.Builder newBuilder() {
    return new example.avro.notification.publish.PublishNotificationContext.Builder();
  }

  /**
   * Creates a new PublishNotificationContext RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PublishNotificationContext RecordBuilder
   */
  public static example.avro.notification.publish.PublishNotificationContext.Builder newBuilder(example.avro.notification.publish.PublishNotificationContext.Builder other) {
    return new example.avro.notification.publish.PublishNotificationContext.Builder(other);
  }

  /**
   * Creates a new PublishNotificationContext RecordBuilder by copying an existing PublishNotificationContext instance.
   * @param other The existing instance to copy.
   * @return A new PublishNotificationContext RecordBuilder
   */
  public static example.avro.notification.publish.PublishNotificationContext.Builder newBuilder(example.avro.notification.publish.PublishNotificationContext other) {
    return new example.avro.notification.publish.PublishNotificationContext.Builder(other);
  }

  /**
   * RecordBuilder for PublishNotificationContext instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PublishNotificationContext>
    implements org.apache.avro.data.RecordBuilder<PublishNotificationContext> {

    private java.lang.CharSequence version;
    private java.lang.CharSequence publishTime;
    private example.avro.notification.publish.NotificationContext notification;
    private example.avro.notification.publish.NotificationContext.Builder notificationBuilder;
    private example.avro.event.publish.EventContext event;
    private example.avro.event.publish.EventContext.Builder eventBuilder;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> metadata;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(example.avro.notification.publish.PublishNotificationContext.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.publishTime)) {
        this.publishTime = data().deepCopy(fields()[1].schema(), other.publishTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.notification)) {
        this.notification = data().deepCopy(fields()[2].schema(), other.notification);
        fieldSetFlags()[2] = true;
      }
      if (other.hasNotificationBuilder()) {
        this.notificationBuilder = example.avro.notification.publish.NotificationContext.newBuilder(other.getNotificationBuilder());
      }
      if (isValidValue(fields()[3], other.event)) {
        this.event = data().deepCopy(fields()[3].schema(), other.event);
        fieldSetFlags()[3] = true;
      }
      if (other.hasEventBuilder()) {
        this.eventBuilder = example.avro.event.publish.EventContext.newBuilder(other.getEventBuilder());
      }
      if (isValidValue(fields()[4], other.metadata)) {
        this.metadata = data().deepCopy(fields()[4].schema(), other.metadata);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing PublishNotificationContext instance
     * @param other The existing instance to copy.
     */
    private Builder(example.avro.notification.publish.PublishNotificationContext other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.publishTime)) {
        this.publishTime = data().deepCopy(fields()[1].schema(), other.publishTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.notification)) {
        this.notification = data().deepCopy(fields()[2].schema(), other.notification);
        fieldSetFlags()[2] = true;
      }
      this.notificationBuilder = null;
      if (isValidValue(fields()[3], other.event)) {
        this.event = data().deepCopy(fields()[3].schema(), other.event);
        fieldSetFlags()[3] = true;
      }
      this.eventBuilder = null;
      if (isValidValue(fields()[4], other.metadata)) {
        this.metadata = data().deepCopy(fields()[4].schema(), other.metadata);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'version' field.
      * @return The value.
      */
    public java.lang.CharSequence getVersion() {
      return version;
    }

    /**
      * Sets the value of the 'version' field.
      * @param value The value of 'version'.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder setVersion(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.version = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder clearVersion() {
      version = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'publishTime' field.
      * @return The value.
      */
    public java.lang.CharSequence getPublishTime() {
      return publishTime;
    }

    /**
      * Sets the value of the 'publishTime' field.
      * @param value The value of 'publishTime'.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder setPublishTime(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.publishTime = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'publishTime' field has been set.
      * @return True if the 'publishTime' field has been set, false otherwise.
      */
    public boolean hasPublishTime() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'publishTime' field.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder clearPublishTime() {
      publishTime = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'notification' field.
      * @return The value.
      */
    public example.avro.notification.publish.NotificationContext getNotification() {
      return notification;
    }

    /**
      * Sets the value of the 'notification' field.
      * @param value The value of 'notification'.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder setNotification(example.avro.notification.publish.NotificationContext value) {
      validate(fields()[2], value);
      this.notificationBuilder = null;
      this.notification = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'notification' field has been set.
      * @return True if the 'notification' field has been set, false otherwise.
      */
    public boolean hasNotification() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'notification' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public example.avro.notification.publish.NotificationContext.Builder getNotificationBuilder() {
      if (notificationBuilder == null) {
        if (hasNotification()) {
          setNotificationBuilder(example.avro.notification.publish.NotificationContext.newBuilder(notification));
        } else {
          setNotificationBuilder(example.avro.notification.publish.NotificationContext.newBuilder());
        }
      }
      return notificationBuilder;
    }

    /**
     * Sets the Builder instance for the 'notification' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public example.avro.notification.publish.PublishNotificationContext.Builder setNotificationBuilder(example.avro.notification.publish.NotificationContext.Builder value) {
      clearNotification();
      notificationBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'notification' field has an active Builder instance
     * @return True if the 'notification' field has an active Builder instance
     */
    public boolean hasNotificationBuilder() {
      return notificationBuilder != null;
    }

    /**
      * Clears the value of the 'notification' field.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder clearNotification() {
      notification = null;
      notificationBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'event' field.
      * @return The value.
      */
    public example.avro.event.publish.EventContext getEvent() {
      return event;
    }

    /**
      * Sets the value of the 'event' field.
      * @param value The value of 'event'.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder setEvent(example.avro.event.publish.EventContext value) {
      validate(fields()[3], value);
      this.eventBuilder = null;
      this.event = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'event' field has been set.
      * @return True if the 'event' field has been set, false otherwise.
      */
    public boolean hasEvent() {
      return fieldSetFlags()[3];
    }

    /**
     * Gets the Builder instance for the 'event' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public example.avro.event.publish.EventContext.Builder getEventBuilder() {
      if (eventBuilder == null) {
        if (hasEvent()) {
          setEventBuilder(example.avro.event.publish.EventContext.newBuilder(event));
        } else {
          setEventBuilder(example.avro.event.publish.EventContext.newBuilder());
        }
      }
      return eventBuilder;
    }

    /**
     * Sets the Builder instance for the 'event' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public example.avro.notification.publish.PublishNotificationContext.Builder setEventBuilder(example.avro.event.publish.EventContext.Builder value) {
      clearEvent();
      eventBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'event' field has an active Builder instance
     * @return True if the 'event' field has an active Builder instance
     */
    public boolean hasEventBuilder() {
      return eventBuilder != null;
    }

    /**
      * Clears the value of the 'event' field.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder clearEvent() {
      event = null;
      eventBuilder = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'metadata' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMetadata() {
      return metadata;
    }

    /**
      * Sets the value of the 'metadata' field.
      * @param value The value of 'metadata'.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder setMetadata(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[4], value);
      this.metadata = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'metadata' field has been set.
      * @return True if the 'metadata' field has been set, false otherwise.
      */
    public boolean hasMetadata() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'metadata' field.
      * @return This builder.
      */
    public example.avro.notification.publish.PublishNotificationContext.Builder clearMetadata() {
      metadata = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PublishNotificationContext build() {
      try {
        PublishNotificationContext record = new PublishNotificationContext();
        record.version = fieldSetFlags()[0] ? this.version : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.publishTime = fieldSetFlags()[1] ? this.publishTime : (java.lang.CharSequence) defaultValue(fields()[1]);
        if (notificationBuilder != null) {
          record.notification = this.notificationBuilder.build();
        } else {
          record.notification = fieldSetFlags()[2] ? this.notification : (example.avro.notification.publish.NotificationContext) defaultValue(fields()[2]);
        }
        if (eventBuilder != null) {
          record.event = this.eventBuilder.build();
        } else {
          record.event = fieldSetFlags()[3] ? this.event : (example.avro.event.publish.EventContext) defaultValue(fields()[3]);
        }
        record.metadata = fieldSetFlags()[4] ? this.metadata : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) defaultValue(fields()[4]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PublishNotificationContext>
    WRITER$ = (org.apache.avro.io.DatumWriter<PublishNotificationContext>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PublishNotificationContext>
    READER$ = (org.apache.avro.io.DatumReader<PublishNotificationContext>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
