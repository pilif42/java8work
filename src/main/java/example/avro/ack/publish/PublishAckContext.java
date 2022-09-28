/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package example.avro.ack.publish;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class PublishAckContext extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7963368213884368937L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PublishAckContext\",\"namespace\":\"example.avro.ack.publish\",\"fields\":[{\"name\":\"version\",\"type\":\"string\",\"default\":\"1.0.0\"},{\"name\":\"publishTime\",\"type\":\"string\"},{\"name\":\"ack\",\"type\":{\"type\":\"record\",\"name\":\"AckContext\",\"fields\":[{\"name\":\"consumerId\",\"type\":\"string\"},{\"name\":\"windowId\",\"type\":\"string\"},{\"name\":\"shardId\",\"type\":\"string\"},{\"name\":\"processedIds\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"failedIds\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PublishAckContext> ENCODER =
      new BinaryMessageEncoder<PublishAckContext>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PublishAckContext> DECODER =
      new BinaryMessageDecoder<PublishAckContext>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<PublishAckContext> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<PublishAckContext> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PublishAckContext>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this PublishAckContext to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a PublishAckContext from a ByteBuffer. */
  public static PublishAckContext fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence version;
  @Deprecated public java.lang.CharSequence publishTime;
  @Deprecated public example.avro.ack.publish.AckContext ack;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PublishAckContext() {}

  /**
   * All-args constructor.
   * @param version The new value for version
   * @param publishTime The new value for publishTime
   * @param ack The new value for ack
   */
  public PublishAckContext(java.lang.CharSequence version, java.lang.CharSequence publishTime, example.avro.ack.publish.AckContext ack) {
    this.version = version;
    this.publishTime = publishTime;
    this.ack = ack;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return version;
    case 1: return publishTime;
    case 2: return ack;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: version = (java.lang.CharSequence)value$; break;
    case 1: publishTime = (java.lang.CharSequence)value$; break;
    case 2: ack = (example.avro.ack.publish.AckContext)value$; break;
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
   * Gets the value of the 'ack' field.
   * @return The value of the 'ack' field.
   */
  public example.avro.ack.publish.AckContext getAck() {
    return ack;
  }

  /**
   * Sets the value of the 'ack' field.
   * @param value the value to set.
   */
  public void setAck(example.avro.ack.publish.AckContext value) {
    this.ack = value;
  }

  /**
   * Creates a new PublishAckContext RecordBuilder.
   * @return A new PublishAckContext RecordBuilder
   */
  public static example.avro.ack.publish.PublishAckContext.Builder newBuilder() {
    return new example.avro.ack.publish.PublishAckContext.Builder();
  }

  /**
   * Creates a new PublishAckContext RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PublishAckContext RecordBuilder
   */
  public static example.avro.ack.publish.PublishAckContext.Builder newBuilder(example.avro.ack.publish.PublishAckContext.Builder other) {
    return new example.avro.ack.publish.PublishAckContext.Builder(other);
  }

  /**
   * Creates a new PublishAckContext RecordBuilder by copying an existing PublishAckContext instance.
   * @param other The existing instance to copy.
   * @return A new PublishAckContext RecordBuilder
   */
  public static example.avro.ack.publish.PublishAckContext.Builder newBuilder(example.avro.ack.publish.PublishAckContext other) {
    return new example.avro.ack.publish.PublishAckContext.Builder(other);
  }

  /**
   * RecordBuilder for PublishAckContext instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PublishAckContext>
    implements org.apache.avro.data.RecordBuilder<PublishAckContext> {

    private java.lang.CharSequence version;
    private java.lang.CharSequence publishTime;
    private example.avro.ack.publish.AckContext ack;
    private example.avro.ack.publish.AckContext.Builder ackBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(example.avro.ack.publish.PublishAckContext.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.publishTime)) {
        this.publishTime = data().deepCopy(fields()[1].schema(), other.publishTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ack)) {
        this.ack = data().deepCopy(fields()[2].schema(), other.ack);
        fieldSetFlags()[2] = true;
      }
      if (other.hasAckBuilder()) {
        this.ackBuilder = example.avro.ack.publish.AckContext.newBuilder(other.getAckBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing PublishAckContext instance
     * @param other The existing instance to copy.
     */
    private Builder(example.avro.ack.publish.PublishAckContext other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.version)) {
        this.version = data().deepCopy(fields()[0].schema(), other.version);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.publishTime)) {
        this.publishTime = data().deepCopy(fields()[1].schema(), other.publishTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ack)) {
        this.ack = data().deepCopy(fields()[2].schema(), other.ack);
        fieldSetFlags()[2] = true;
      }
      this.ackBuilder = null;
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
    public example.avro.ack.publish.PublishAckContext.Builder setVersion(java.lang.CharSequence value) {
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
    public example.avro.ack.publish.PublishAckContext.Builder clearVersion() {
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
    public example.avro.ack.publish.PublishAckContext.Builder setPublishTime(java.lang.CharSequence value) {
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
    public example.avro.ack.publish.PublishAckContext.Builder clearPublishTime() {
      publishTime = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'ack' field.
      * @return The value.
      */
    public example.avro.ack.publish.AckContext getAck() {
      return ack;
    }

    /**
      * Sets the value of the 'ack' field.
      * @param value The value of 'ack'.
      * @return This builder.
      */
    public example.avro.ack.publish.PublishAckContext.Builder setAck(example.avro.ack.publish.AckContext value) {
      validate(fields()[2], value);
      this.ackBuilder = null;
      this.ack = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'ack' field has been set.
      * @return True if the 'ack' field has been set, false otherwise.
      */
    public boolean hasAck() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'ack' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public example.avro.ack.publish.AckContext.Builder getAckBuilder() {
      if (ackBuilder == null) {
        if (hasAck()) {
          setAckBuilder(example.avro.ack.publish.AckContext.newBuilder(ack));
        } else {
          setAckBuilder(example.avro.ack.publish.AckContext.newBuilder());
        }
      }
      return ackBuilder;
    }

    /**
     * Sets the Builder instance for the 'ack' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public example.avro.ack.publish.PublishAckContext.Builder setAckBuilder(example.avro.ack.publish.AckContext.Builder value) {
      clearAck();
      ackBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'ack' field has an active Builder instance
     * @return True if the 'ack' field has an active Builder instance
     */
    public boolean hasAckBuilder() {
      return ackBuilder != null;
    }

    /**
      * Clears the value of the 'ack' field.
      * @return This builder.
      */
    public example.avro.ack.publish.PublishAckContext.Builder clearAck() {
      ack = null;
      ackBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PublishAckContext build() {
      try {
        PublishAckContext record = new PublishAckContext();
        record.version = fieldSetFlags()[0] ? this.version : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.publishTime = fieldSetFlags()[1] ? this.publishTime : (java.lang.CharSequence) defaultValue(fields()[1]);
        if (ackBuilder != null) {
          record.ack = this.ackBuilder.build();
        } else {
          record.ack = fieldSetFlags()[2] ? this.ack : (example.avro.ack.publish.AckContext) defaultValue(fields()[2]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PublishAckContext>
    WRITER$ = (org.apache.avro.io.DatumWriter<PublishAckContext>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PublishAckContext>
    READER$ = (org.apache.avro.io.DatumReader<PublishAckContext>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
