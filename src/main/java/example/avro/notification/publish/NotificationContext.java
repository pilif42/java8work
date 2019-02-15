/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package example.avro.notification.publish;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class NotificationContext extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1835256900626582888L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NotificationContext\",\"namespace\":\"example.avro.notification.publish\",\"fields\":[{\"name\":\"notifications\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Notification\",\"fields\":[{\"name\":\"notificationType\",\"type\":{\"type\":\"enum\",\"name\":\"NotificationType\",\"symbols\":[\"error\",\"warning\",\"information\",\"unclassified\"],\"default\":\"unclassified\"}},{\"name\":\"code\",\"type\":\"string\",\"default\":\"0\"},{\"name\":\"description\",\"type\":\"string\"}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<NotificationContext> ENCODER =
      new BinaryMessageEncoder<NotificationContext>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<NotificationContext> DECODER =
      new BinaryMessageDecoder<NotificationContext>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<NotificationContext> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<NotificationContext> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<NotificationContext>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this NotificationContext to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a NotificationContext from a ByteBuffer. */
  public static NotificationContext fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.util.List<example.avro.notification.publish.Notification> notifications;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NotificationContext() {}

  /**
   * All-args constructor.
   * @param notifications The new value for notifications
   */
  public NotificationContext(java.util.List<example.avro.notification.publish.Notification> notifications) {
    this.notifications = notifications;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return notifications;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: notifications = (java.util.List<example.avro.notification.publish.Notification>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'notifications' field.
   * @return The value of the 'notifications' field.
   */
  public java.util.List<example.avro.notification.publish.Notification> getNotifications() {
    return notifications;
  }

  /**
   * Sets the value of the 'notifications' field.
   * @param value the value to set.
   */
  public void setNotifications(java.util.List<example.avro.notification.publish.Notification> value) {
    this.notifications = value;
  }

  /**
   * Creates a new NotificationContext RecordBuilder.
   * @return A new NotificationContext RecordBuilder
   */
  public static example.avro.notification.publish.NotificationContext.Builder newBuilder() {
    return new example.avro.notification.publish.NotificationContext.Builder();
  }

  /**
   * Creates a new NotificationContext RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NotificationContext RecordBuilder
   */
  public static example.avro.notification.publish.NotificationContext.Builder newBuilder(example.avro.notification.publish.NotificationContext.Builder other) {
    return new example.avro.notification.publish.NotificationContext.Builder(other);
  }

  /**
   * Creates a new NotificationContext RecordBuilder by copying an existing NotificationContext instance.
   * @param other The existing instance to copy.
   * @return A new NotificationContext RecordBuilder
   */
  public static example.avro.notification.publish.NotificationContext.Builder newBuilder(example.avro.notification.publish.NotificationContext other) {
    return new example.avro.notification.publish.NotificationContext.Builder(other);
  }

  /**
   * RecordBuilder for NotificationContext instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NotificationContext>
    implements org.apache.avro.data.RecordBuilder<NotificationContext> {

    private java.util.List<example.avro.notification.publish.Notification> notifications;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(example.avro.notification.publish.NotificationContext.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.notifications)) {
        this.notifications = data().deepCopy(fields()[0].schema(), other.notifications);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing NotificationContext instance
     * @param other The existing instance to copy.
     */
    private Builder(example.avro.notification.publish.NotificationContext other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.notifications)) {
        this.notifications = data().deepCopy(fields()[0].schema(), other.notifications);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'notifications' field.
      * @return The value.
      */
    public java.util.List<example.avro.notification.publish.Notification> getNotifications() {
      return notifications;
    }

    /**
      * Sets the value of the 'notifications' field.
      * @param value The value of 'notifications'.
      * @return This builder.
      */
    public example.avro.notification.publish.NotificationContext.Builder setNotifications(java.util.List<example.avro.notification.publish.Notification> value) {
      validate(fields()[0], value);
      this.notifications = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'notifications' field has been set.
      * @return True if the 'notifications' field has been set, false otherwise.
      */
    public boolean hasNotifications() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'notifications' field.
      * @return This builder.
      */
    public example.avro.notification.publish.NotificationContext.Builder clearNotifications() {
      notifications = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NotificationContext build() {
      try {
        NotificationContext record = new NotificationContext();
        record.notifications = fieldSetFlags()[0] ? this.notifications : (java.util.List<example.avro.notification.publish.Notification>) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NotificationContext>
    WRITER$ = (org.apache.avro.io.DatumWriter<NotificationContext>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NotificationContext>
    READER$ = (org.apache.avro.io.DatumReader<NotificationContext>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
