// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sme.proto

package com.sme.app.proto;

/**
 * Protobuf type {@code com.sme.app.proto.Employee}
 */
public  final class Employee extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.sme.app.proto.Employee)
    EmployeeOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Employee.newBuilder() to construct.
  private Employee(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Employee() {
    firstName_ = "";
    lastName_ = "";
    email_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Employee();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Employee(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            id_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            firstName_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            lastName_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            email_ = s;
            break;
          }
          case 41: {

            salary_ = input.readDouble();
            break;
          }
          case 50: {
            com.sme.app.proto.Department.Builder subBuilder = null;
            if (department_ != null) {
              subBuilder = department_.toBuilder();
            }
            department_ = input.readMessage(com.sme.app.proto.Department.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(department_);
              department_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.sme.app.proto.Sme.internal_static_com_sme_app_proto_Employee_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.sme.app.proto.Sme.internal_static_com_sme_app_proto_Employee_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.sme.app.proto.Employee.class, com.sme.app.proto.Employee.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  public int getId() {
    return id_;
  }

  public static final int FIRST_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object firstName_;
  /**
   * <code>string first_name = 2;</code>
   * @return The firstName.
   */
  public java.lang.String getFirstName() {
    java.lang.Object ref = firstName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      firstName_ = s;
      return s;
    }
  }
  /**
   * <code>string first_name = 2;</code>
   * @return The bytes for firstName.
   */
  public com.google.protobuf.ByteString
      getFirstNameBytes() {
    java.lang.Object ref = firstName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      firstName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LAST_NAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object lastName_;
  /**
   * <code>string last_name = 3;</code>
   * @return The lastName.
   */
  public java.lang.String getLastName() {
    java.lang.Object ref = lastName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      lastName_ = s;
      return s;
    }
  }
  /**
   * <code>string last_name = 3;</code>
   * @return The bytes for lastName.
   */
  public com.google.protobuf.ByteString
      getLastNameBytes() {
    java.lang.Object ref = lastName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      lastName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EMAIL_FIELD_NUMBER = 4;
  private volatile java.lang.Object email_;
  /**
   * <code>string email = 4;</code>
   * @return The email.
   */
  public java.lang.String getEmail() {
    java.lang.Object ref = email_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      email_ = s;
      return s;
    }
  }
  /**
   * <code>string email = 4;</code>
   * @return The bytes for email.
   */
  public com.google.protobuf.ByteString
      getEmailBytes() {
    java.lang.Object ref = email_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      email_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SALARY_FIELD_NUMBER = 5;
  private double salary_;
  /**
   * <code>double salary = 5;</code>
   * @return The salary.
   */
  public double getSalary() {
    return salary_;
  }

  public static final int DEPARTMENT_FIELD_NUMBER = 6;
  private com.sme.app.proto.Department department_;
  /**
   * <code>.com.sme.app.proto.Department department = 6;</code>
   * @return Whether the department field is set.
   */
  public boolean hasDepartment() {
    return department_ != null;
  }
  /**
   * <code>.com.sme.app.proto.Department department = 6;</code>
   * @return The department.
   */
  public com.sme.app.proto.Department getDepartment() {
    return department_ == null ? com.sme.app.proto.Department.getDefaultInstance() : department_;
  }
  /**
   * <code>.com.sme.app.proto.Department department = 6;</code>
   */
  public com.sme.app.proto.DepartmentOrBuilder getDepartmentOrBuilder() {
    return getDepartment();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (!getFirstNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, firstName_);
    }
    if (!getLastNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, lastName_);
    }
    if (!getEmailBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, email_);
    }
    if (salary_ != 0D) {
      output.writeDouble(5, salary_);
    }
    if (department_ != null) {
      output.writeMessage(6, getDepartment());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (!getFirstNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, firstName_);
    }
    if (!getLastNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, lastName_);
    }
    if (!getEmailBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, email_);
    }
    if (salary_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, salary_);
    }
    if (department_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(6, getDepartment());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.sme.app.proto.Employee)) {
      return super.equals(obj);
    }
    com.sme.app.proto.Employee other = (com.sme.app.proto.Employee) obj;

    if (getId()
        != other.getId()) return false;
    if (!getFirstName()
        .equals(other.getFirstName())) return false;
    if (!getLastName()
        .equals(other.getLastName())) return false;
    if (!getEmail()
        .equals(other.getEmail())) return false;
    if (java.lang.Double.doubleToLongBits(getSalary())
        != java.lang.Double.doubleToLongBits(
            other.getSalary())) return false;
    if (hasDepartment() != other.hasDepartment()) return false;
    if (hasDepartment()) {
      if (!getDepartment()
          .equals(other.getDepartment())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + FIRST_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getFirstName().hashCode();
    hash = (37 * hash) + LAST_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getLastName().hashCode();
    hash = (37 * hash) + EMAIL_FIELD_NUMBER;
    hash = (53 * hash) + getEmail().hashCode();
    hash = (37 * hash) + SALARY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSalary()));
    if (hasDepartment()) {
      hash = (37 * hash) + DEPARTMENT_FIELD_NUMBER;
      hash = (53 * hash) + getDepartment().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.sme.app.proto.Employee parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sme.app.proto.Employee parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sme.app.proto.Employee parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sme.app.proto.Employee parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sme.app.proto.Employee parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sme.app.proto.Employee parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sme.app.proto.Employee parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sme.app.proto.Employee parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sme.app.proto.Employee parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.sme.app.proto.Employee parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sme.app.proto.Employee parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sme.app.proto.Employee parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.sme.app.proto.Employee prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.sme.app.proto.Employee}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.sme.app.proto.Employee)
      com.sme.app.proto.EmployeeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.sme.app.proto.Sme.internal_static_com_sme_app_proto_Employee_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sme.app.proto.Sme.internal_static_com_sme_app_proto_Employee_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sme.app.proto.Employee.class, com.sme.app.proto.Employee.Builder.class);
    }

    // Construct using com.sme.app.proto.Employee.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = 0;

      firstName_ = "";

      lastName_ = "";

      email_ = "";

      salary_ = 0D;

      if (departmentBuilder_ == null) {
        department_ = null;
      } else {
        department_ = null;
        departmentBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.sme.app.proto.Sme.internal_static_com_sme_app_proto_Employee_descriptor;
    }

    @java.lang.Override
    public com.sme.app.proto.Employee getDefaultInstanceForType() {
      return com.sme.app.proto.Employee.getDefaultInstance();
    }

    @java.lang.Override
    public com.sme.app.proto.Employee build() {
      com.sme.app.proto.Employee result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.sme.app.proto.Employee buildPartial() {
      com.sme.app.proto.Employee result = new com.sme.app.proto.Employee(this);
      result.id_ = id_;
      result.firstName_ = firstName_;
      result.lastName_ = lastName_;
      result.email_ = email_;
      result.salary_ = salary_;
      if (departmentBuilder_ == null) {
        result.department_ = department_;
      } else {
        result.department_ = departmentBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.sme.app.proto.Employee) {
        return mergeFrom((com.sme.app.proto.Employee)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.sme.app.proto.Employee other) {
      if (other == com.sme.app.proto.Employee.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (!other.getFirstName().isEmpty()) {
        firstName_ = other.firstName_;
        onChanged();
      }
      if (!other.getLastName().isEmpty()) {
        lastName_ = other.lastName_;
        onChanged();
      }
      if (!other.getEmail().isEmpty()) {
        email_ = other.email_;
        onChanged();
      }
      if (other.getSalary() != 0D) {
        setSalary(other.getSalary());
      }
      if (other.hasDepartment()) {
        mergeDepartment(other.getDepartment());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.sme.app.proto.Employee parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.sme.app.proto.Employee) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object firstName_ = "";
    /**
     * <code>string first_name = 2;</code>
     * @return The firstName.
     */
    public java.lang.String getFirstName() {
      java.lang.Object ref = firstName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        firstName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string first_name = 2;</code>
     * @return The bytes for firstName.
     */
    public com.google.protobuf.ByteString
        getFirstNameBytes() {
      java.lang.Object ref = firstName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        firstName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string first_name = 2;</code>
     * @param value The firstName to set.
     * @return This builder for chaining.
     */
    public Builder setFirstName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      firstName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string first_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearFirstName() {
      
      firstName_ = getDefaultInstance().getFirstName();
      onChanged();
      return this;
    }
    /**
     * <code>string first_name = 2;</code>
     * @param value The bytes for firstName to set.
     * @return This builder for chaining.
     */
    public Builder setFirstNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      firstName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object lastName_ = "";
    /**
     * <code>string last_name = 3;</code>
     * @return The lastName.
     */
    public java.lang.String getLastName() {
      java.lang.Object ref = lastName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        lastName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string last_name = 3;</code>
     * @return The bytes for lastName.
     */
    public com.google.protobuf.ByteString
        getLastNameBytes() {
      java.lang.Object ref = lastName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lastName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string last_name = 3;</code>
     * @param value The lastName to set.
     * @return This builder for chaining.
     */
    public Builder setLastName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      lastName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string last_name = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearLastName() {
      
      lastName_ = getDefaultInstance().getLastName();
      onChanged();
      return this;
    }
    /**
     * <code>string last_name = 3;</code>
     * @param value The bytes for lastName to set.
     * @return This builder for chaining.
     */
    public Builder setLastNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      lastName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object email_ = "";
    /**
     * <code>string email = 4;</code>
     * @return The email.
     */
    public java.lang.String getEmail() {
      java.lang.Object ref = email_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        email_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string email = 4;</code>
     * @return The bytes for email.
     */
    public com.google.protobuf.ByteString
        getEmailBytes() {
      java.lang.Object ref = email_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        email_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string email = 4;</code>
     * @param value The email to set.
     * @return This builder for chaining.
     */
    public Builder setEmail(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      email_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string email = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearEmail() {
      
      email_ = getDefaultInstance().getEmail();
      onChanged();
      return this;
    }
    /**
     * <code>string email = 4;</code>
     * @param value The bytes for email to set.
     * @return This builder for chaining.
     */
    public Builder setEmailBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      email_ = value;
      onChanged();
      return this;
    }

    private double salary_ ;
    /**
     * <code>double salary = 5;</code>
     * @return The salary.
     */
    public double getSalary() {
      return salary_;
    }
    /**
     * <code>double salary = 5;</code>
     * @param value The salary to set.
     * @return This builder for chaining.
     */
    public Builder setSalary(double value) {
      
      salary_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double salary = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearSalary() {
      
      salary_ = 0D;
      onChanged();
      return this;
    }

    private com.sme.app.proto.Department department_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.sme.app.proto.Department, com.sme.app.proto.Department.Builder, com.sme.app.proto.DepartmentOrBuilder> departmentBuilder_;
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     * @return Whether the department field is set.
     */
    public boolean hasDepartment() {
      return departmentBuilder_ != null || department_ != null;
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     * @return The department.
     */
    public com.sme.app.proto.Department getDepartment() {
      if (departmentBuilder_ == null) {
        return department_ == null ? com.sme.app.proto.Department.getDefaultInstance() : department_;
      } else {
        return departmentBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    public Builder setDepartment(com.sme.app.proto.Department value) {
      if (departmentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        department_ = value;
        onChanged();
      } else {
        departmentBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    public Builder setDepartment(
        com.sme.app.proto.Department.Builder builderForValue) {
      if (departmentBuilder_ == null) {
        department_ = builderForValue.build();
        onChanged();
      } else {
        departmentBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    public Builder mergeDepartment(com.sme.app.proto.Department value) {
      if (departmentBuilder_ == null) {
        if (department_ != null) {
          department_ =
            com.sme.app.proto.Department.newBuilder(department_).mergeFrom(value).buildPartial();
        } else {
          department_ = value;
        }
        onChanged();
      } else {
        departmentBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    public Builder clearDepartment() {
      if (departmentBuilder_ == null) {
        department_ = null;
        onChanged();
      } else {
        department_ = null;
        departmentBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    public com.sme.app.proto.Department.Builder getDepartmentBuilder() {
      
      onChanged();
      return getDepartmentFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    public com.sme.app.proto.DepartmentOrBuilder getDepartmentOrBuilder() {
      if (departmentBuilder_ != null) {
        return departmentBuilder_.getMessageOrBuilder();
      } else {
        return department_ == null ?
            com.sme.app.proto.Department.getDefaultInstance() : department_;
      }
    }
    /**
     * <code>.com.sme.app.proto.Department department = 6;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.sme.app.proto.Department, com.sme.app.proto.Department.Builder, com.sme.app.proto.DepartmentOrBuilder> 
        getDepartmentFieldBuilder() {
      if (departmentBuilder_ == null) {
        departmentBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.sme.app.proto.Department, com.sme.app.proto.Department.Builder, com.sme.app.proto.DepartmentOrBuilder>(
                getDepartment(),
                getParentForChildren(),
                isClean());
        department_ = null;
      }
      return departmentBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.sme.app.proto.Employee)
  }

  // @@protoc_insertion_point(class_scope:com.sme.app.proto.Employee)
  private static final com.sme.app.proto.Employee DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.sme.app.proto.Employee();
  }

  public static com.sme.app.proto.Employee getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Employee>
      PARSER = new com.google.protobuf.AbstractParser<Employee>() {
    @java.lang.Override
    public Employee parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Employee(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Employee> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Employee> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.sme.app.proto.Employee getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

