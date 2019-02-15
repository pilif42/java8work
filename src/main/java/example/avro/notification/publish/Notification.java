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
public class Notification extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7766400484623381796L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Notification\",\"namespace\":\"example.avro.notification.publish\",\"fields\":[{\"name\":\"notificationType\",\"type\":{\"type\":\"enum\",\"name\":\"NotificationType\",\"symbols\":[\"error\",\"warning\",\"information\",\"unclassified\"],\"default\":\"unclassified\"}},{\"name\":\"code\",\"type\":\"string\",\"default\":\"0\"},{\"name\":\"description\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Notification> ENCODER =
      new BinaryMessageEncoder<Notification>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Notification> DECODER =
      new BinaryMessageDecoder<Notification>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Notification> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Notification> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Notification>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Notification to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Notification from a ByteBuffer. */
  public static Notification fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public example.avro.notification.publish.NotificationType notificationType;
  @Deprecated public java.lang.CharSequence code;
  @Deprecated public java.lang.CharSequence description;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Notification() {}

  /**
   * All-args constructor.
   * @param notificationType The new value for notificationType
   * @param code The new value for code
   * @param description The new value for description
   */
  public Notification(example.avro.notification.publish.NotificationType notificationType, java.lang.CharSequence code, java.lang.CharSequence description) {
    this.notificationType = notificationType;
    this.code = code;
    this.description = description;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return notificationType;
    case 1: return code;
    case 2: return description;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: notificationType = (example.avro.notification.publish.NotificationType)value$; break;
    case 1: code = (java.lang.CharSequence)value$; break;
    case 2: description = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'notificationType' field.
   * @return The value of the 'notificationType' field.
   */
  public example.avro.notification.publish.NotificationType getNotificationType() {
    return notificationType;
  }

  /**
   * Sets the value of the 'notificationType' field.
   * @param value the value to set.
   */
  public void setNotificationType(example.avro.notification.publish.NotificationType value) {
    this.notificationType = value;
  }

  /**
   * Gets the value of the 'code' field.
   * @return The value of the 'code' field.
   */
  public java.lang.CharSequence getCode() {
    return code;
  }

  /**
   * Sets the value of the 'code' field.
   * @param value the value to set.
   */
  public void setCode(java.lang.CharSequence value) {
    this.code = value;
  }

  /**
   * Gets the value of the 'description' field.
   * @return The value of the 'description' field.
   */
  public java.lang.CharSequence getDescription() {
    return description;
  }

  /**
   * Sets the value of the 'description' field.
   * @param value the value to set.
   */
  public void setDescription(java.lang.CharSequence value) {
    this.description = value;
  }

  /**
   * Creates a new Notification RecordBuilder.
   * @return A new Notification RecordBuilder
   */
  public static example.avro.notification.publish.Notification.Builder newBuilder() {
    return new example.avro.notification.publish.Notification.Builder();
  }

  /**
   * Creates a new Notification RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Notification RecordBuilder
   */
  public static example.avro.notification.publish.Notification.Builder newBuilder(example.avro.notification.publish.Notification.Builder other) {
    return new example.avro.notification.publish.Notification.Builder(other);
  }

  /**
   * Creates a new Notification RecordBuilder by copying an existing Notification instance.
   * @param other The existing instance to copy.
   * @return A new Notification RecordBuilder
   */
  public static example.avro.notification.publish.Notification.Builder newBuilder(example.avro.notification.publish.Notification other) {
    return new example.avro.notification.publish.Notification.Builder(other);
  }

  /**
   * RecordBuilder for Notification instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Notification>
    implements org.apache.avro.data.RecordBuilder<Notification> {

    private example.avro.notification.publish.NotificationType notificationType;
    private java.lang.CharSequence code;
    private java.lang.CharSequence description;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(example.avro.notification.publish.Notification.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.notificationType)) {
        this.notificationType = data().deepCopy(fields()[0].schema(), other.notificationType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.code)) {
        this.code = data().deepCopy(fields()[1].schema(), other.code);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.description)) {
        this.description = data().deepCopy(fields()[2].schema(), other.description);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Notification instance
     * @param other The existing instance to copy.
     */
    private Builder(example.avro.notification.publish.Notification other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.notificationType)) {
        this.notificationType = data().deepCopy(fields()[0].schema(), other.notificationType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.code)) {
        this.code = data().deepCopy(fields()[1].schema(), other.code);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.description)) {
        this.description = data().deepCopy(fields()[2].schema(), other.description);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'notificationType' field.
      * @return The value.
      */
    public example.avro.notification.publish.NotificationType getNotificationType() {
      return notificationType;
    }

    /**
      * Sets the value of the 'notificationType' field.
      * @param value The value of 'notificationType'.
      * @return This builder.
      */
    public example.avro.notification.publish.Notification.Builder setNotificationType(example.avro.notification.publish.NotificationType value) {
      validate(fields()[0], value);
      this.notificationType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'notificationType' field has been set.
      * @return True if the 'notificationType' field has been set, false otherwise.
      */
    public boolean hasNotificationType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'notificationType' field.
      * @return This builder.
      */
    public example.avro.notification.publish.Notification.Builder clearNotificationType() {
      notificationType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'code' field.
      * @return The value.
      */
    public java.lang.CharSequence getCode() {
      return code;
    }

    /**
      * Sets the value of the 'code' field.
      * @param value The value of 'code'.
      * @return This builder.
      */
    public example.avro.notification.publish.Notification.Builder setCode(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.code = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'code' field has been set.
      * @return True if the 'code' field has been set, false otherwise.
      */
    public boolean hasCode() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'code' field.
      * @return This builder.
      */
    public example.avro.notification.publish.Notification.Builder clearCode() {
      code = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * @return The value.
      */
    public java.lang.CharSequence getDescription() {
      return description;
    }

    /**
      * Sets the value of the 'description' field.
      * @param value The value of 'description'.
      * @return This builder.
      */
    public example.avro.notification.publish.Notification.Builder setDescription(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.description = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'description' field has been set.
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'description' field.
      * @return This builder.
      */
    public example.avro.notification.publish.Notification.Builder clearDescription() {
      description = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Notification build() {
      try {
        Notification record = new Notification();
        record.notificationType = fieldSetFlags()[0] ? this.notificationType : (example.avro.notification.publish.NotificationType) defaultValue(fields()[0]);
        record.code = fieldSetFlags()[1] ? this.code : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.description = fieldSetFlags()[2] ? this.description : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Notification>
    WRITER$ = (org.apache.avro.io.DatumWriter<Notification>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Notification>
    READER$ = (org.apache.avro.io.DatumReader<Notification>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
