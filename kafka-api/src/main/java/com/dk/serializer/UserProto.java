package com.dk.serializer;

/**
 * @author DK
 * @version 1.0
 * @date 2020/11/6 16:03
 **/
public final class UserProto {
    private UserProto() {}
    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }
    public interface UserOrBuilder extends
            // @@protoc_insertion_point(interface_extends:kafka.User)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>required int64 id = 1;</code>
         * @return Whether the id field is set.
         */
        boolean hasId();
        /**
         * <code>required int64 id = 1;</code>
         * @return The id.
         */
        long getId();

        /**
         * <code>required string name = 2;</code>
         * @return Whether the name field is set.
         */
        boolean hasName();
        /**
         * <code>required string name = 2;</code>
         * @return The name.
         */
        String getName();
        /**
         * <code>required string name = 2;</code>
         * @return The bytes for name.
         */
        com.google.protobuf.ByteString
        getNameBytes();

        /**
         * <code>required int32 gender = 3;</code>
         * @return Whether the gender field is set.
         */
        boolean hasGender();
        /**
         * <code>required int32 gender = 3;</code>
         * @return The gender.
         */
        int getGender();

        /**
         * <code>optional string phone = 4;</code>
         * @return Whether the phone field is set.
         */
        boolean hasPhone();
        /**
         * <code>optional string phone = 4;</code>
         * @return The phone.
         */
        String getPhone();
        /**
         * <code>optional string phone = 4;</code>
         * @return The bytes for phone.
         */
        com.google.protobuf.ByteString
        getPhoneBytes();
    }
    /**
     * Protobuf type {@code kafka.User}
     */
    public static final class User extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:kafka.User)
            UserOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use User.newBuilder() to construct.
        private User(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private User() {
            name_ = "";
            phone_ = "";
        }

        @Override
        @SuppressWarnings({"unused"})
        protected Object newInstance(
                UnusedPrivateParameter unused) {
            return new User();
        }

        @Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private User(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new NullPointerException();
            }
            int mutable_bitField0_ = 0;
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
                            bitField0_ |= 0x00000001;
                            id_ = input.readInt64();
                            break;
                        }
                        case 18: {
                            com.google.protobuf.ByteString bs = input.readBytes();
                            bitField0_ |= 0x00000002;
                            name_ = bs;
                            break;
                        }
                        case 24: {
                            bitField0_ |= 0x00000004;
                            gender_ = input.readInt32();
                            break;
                        }
                        case 34: {
                            com.google.protobuf.ByteString bs = input.readBytes();
                            bitField0_ |= 0x00000008;
                            phone_ = bs;
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
            return UserProto.internal_static_kafka_User_descriptor;
        }

        @Override
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return UserProto.internal_static_kafka_User_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            User.class, Builder.class);
        }

        private int bitField0_;
        public static final int ID_FIELD_NUMBER = 1;
        private long id_;
        /**
         * <code>required int64 id = 1;</code>
         * @return Whether the id field is set.
         */
        @Override
        public boolean hasId() {
            return ((bitField0_ & 0x00000001) != 0);
        }
        /**
         * <code>required int64 id = 1;</code>
         * @return The id.
         */
        @Override
        public long getId() {
            return id_;
        }

        public static final int NAME_FIELD_NUMBER = 2;
        private volatile Object name_;
        /**
         * <code>required string name = 2;</code>
         * @return Whether the name field is set.
         */
        @Override
        public boolean hasName() {
            return ((bitField0_ & 0x00000002) != 0);
        }
        /**
         * <code>required string name = 2;</code>
         * @return The name.
         */
        @Override
        public String getName() {
            Object ref = name_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    name_ = s;
                }
                return s;
            }
        }
        /**
         * <code>required string name = 2;</code>
         * @return The bytes for name.
         */
        @Override
        public com.google.protobuf.ByteString
        getNameBytes() {
            Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int GENDER_FIELD_NUMBER = 3;
        private int gender_;
        /**
         * <code>required int32 gender = 3;</code>
         * @return Whether the gender field is set.
         */
        @Override
        public boolean hasGender() {
            return ((bitField0_ & 0x00000004) != 0);
        }
        /**
         * <code>required int32 gender = 3;</code>
         * @return The gender.
         */
        @Override
        public int getGender() {
            return gender_;
        }

        public static final int PHONE_FIELD_NUMBER = 4;
        private volatile Object phone_;
        /**
         * <code>optional string phone = 4;</code>
         * @return Whether the phone field is set.
         */
        @Override
        public boolean hasPhone() {
            return ((bitField0_ & 0x00000008) != 0);
        }
        /**
         * <code>optional string phone = 4;</code>
         * @return The phone.
         */
        @Override
        public String getPhone() {
            Object ref = phone_;
            if (ref instanceof String) {
                return (String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    phone_ = s;
                }
                return s;
            }
        }
        /**
         * <code>optional string phone = 4;</code>
         * @return The bytes for phone.
         */
        @Override
        public com.google.protobuf.ByteString
        getPhoneBytes() {
            Object ref = phone_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (String) ref);
                phone_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;
        @Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            if (!hasId()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasName()) {
                memoizedIsInitialized = 0;
                return false;
            }
            if (!hasGender()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (((bitField0_ & 0x00000001) != 0)) {
                output.writeInt64(1, id_);
            }
            if (((bitField0_ & 0x00000002) != 0)) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
            }
            if (((bitField0_ & 0x00000004) != 0)) {
                output.writeInt32(3, gender_);
            }
            if (((bitField0_ & 0x00000008) != 0)) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 4, phone_);
            }
            unknownFields.writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) != 0)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt64Size(1, id_);
            }
            if (((bitField0_ & 0x00000002) != 0)) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
            }
            if (((bitField0_ & 0x00000004) != 0)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(3, gender_);
            }
            if (((bitField0_ & 0x00000008) != 0)) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, phone_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof User)) {
                return super.equals(obj);
            }
            User other = (User) obj;

            if (hasId() != other.hasId()) return false;
            if (hasId()) {
                if (getId()
                        != other.getId()) return false;
            }
            if (hasName() != other.hasName()) return false;
            if (hasName()) {
                if (!getName()
                        .equals(other.getName())) return false;
            }
            if (hasGender() != other.hasGender()) return false;
            if (hasGender()) {
                if (getGender()
                        != other.getGender()) return false;
            }
            if (hasPhone() != other.hasPhone()) return false;
            if (hasPhone()) {
                if (!getPhone()
                        .equals(other.getPhone())) return false;
            }
            if (!unknownFields.equals(other.unknownFields)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            if (hasId()) {
                hash = (37 * hash) + ID_FIELD_NUMBER;
                hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
                        getId());
            }
            if (hasName()) {
                hash = (37 * hash) + NAME_FIELD_NUMBER;
                hash = (53 * hash) + getName().hashCode();
            }
            if (hasGender()) {
                hash = (37 * hash) + GENDER_FIELD_NUMBER;
                hash = (53 * hash) + getGender();
            }
            if (hasPhone()) {
                hash = (37 * hash) + PHONE_FIELD_NUMBER;
                hash = (53 * hash) + getPhone().hashCode();
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static User parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static User parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static User parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static User parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static User parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static User parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static User parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static User parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static User parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static User parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static User parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static User parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(User prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }
        @Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override
        protected Builder newBuilderForType(
                BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }
        /**
         * Protobuf type {@code kafka.User}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:kafka.User)
                UserOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return UserProto.internal_static_kafka_User_descriptor;
            }

            @Override
            protected FieldAccessorTable
            internalGetFieldAccessorTable() {
                return UserProto.internal_static_kafka_User_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                User.class, Builder.class);
            }

            // Construct using com.qingshan.serializer.UserProto.User.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @Override
            public Builder clear() {
                super.clear();
                id_ = 0L;
                bitField0_ = (bitField0_ & ~0x00000001);
                name_ = "";
                bitField0_ = (bitField0_ & ~0x00000002);
                gender_ = 0;
                bitField0_ = (bitField0_ & ~0x00000004);
                phone_ = "";
                bitField0_ = (bitField0_ & ~0x00000008);
                return this;
            }

            @Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return UserProto.internal_static_kafka_User_descriptor;
            }

            @Override
            public User getDefaultInstanceForType() {
                return User.getDefaultInstance();
            }

            @Override
            public User build() {
                User result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public User buildPartial() {
                User result = new User(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) != 0)) {
                    result.id_ = id_;
                    to_bitField0_ |= 0x00000001;
                }
                if (((from_bitField0_ & 0x00000002) != 0)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.name_ = name_;
                if (((from_bitField0_ & 0x00000004) != 0)) {
                    result.gender_ = gender_;
                    to_bitField0_ |= 0x00000004;
                }
                if (((from_bitField0_ & 0x00000008) != 0)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.phone_ = phone_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override
            public Builder clone() {
                return super.clone();
            }
            @Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return super.setField(field, value);
            }
            @Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }
            @Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }
            @Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return super.setRepeatedField(field, index, value);
            }
            @Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return super.addRepeatedField(field, value);
            }
            @Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof User) {
                    return mergeFrom((User)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(User other) {
                if (other == User.getDefaultInstance()) return this;
                if (other.hasId()) {
                    setId(other.getId());
                }
                if (other.hasName()) {
                    bitField0_ |= 0x00000002;
                    name_ = other.name_;
                    onChanged();
                }
                if (other.hasGender()) {
                    setGender(other.getGender());
                }
                if (other.hasPhone()) {
                    bitField0_ |= 0x00000008;
                    phone_ = other.phone_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!hasId()) {
                    return false;
                }
                if (!hasName()) {
                    return false;
                }
                if (!hasGender()) {
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                User parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (User) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
            private int bitField0_;

            private long id_ ;
            /**
             * <code>required int64 id = 1;</code>
             * @return Whether the id field is set.
             */
            @Override
            public boolean hasId() {
                return ((bitField0_ & 0x00000001) != 0);
            }
            /**
             * <code>required int64 id = 1;</code>
             * @return The id.
             */
            @Override
            public long getId() {
                return id_;
            }
            /**
             * <code>required int64 id = 1;</code>
             * @param value The id to set.
             * @return This builder for chaining.
             */
            public Builder setId(long value) {
                bitField0_ |= 0x00000001;
                id_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>required int64 id = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearId() {
                bitField0_ = (bitField0_ & ~0x00000001);
                id_ = 0L;
                onChanged();
                return this;
            }

            private Object name_ = "";
            /**
             * <code>required string name = 2;</code>
             * @return Whether the name field is set.
             */
            @Override
            public boolean hasName() {
                return ((bitField0_ & 0x00000002) != 0);
            }
            /**
             * <code>required string name = 2;</code>
             * @return The name.
             */
            @Override
            public String getName() {
                Object ref = name_;
                if (!(ref instanceof String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        name_ = s;
                    }
                    return s;
                } else {
                    return (String) ref;
                }
            }
            /**
             * <code>required string name = 2;</code>
             * @return The bytes for name.
             */
            @Override
            public com.google.protobuf.ByteString
            getNameBytes() {
                Object ref = name_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (String) ref);
                    name_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>required string name = 2;</code>
             * @param value The name to set.
             * @return This builder for chaining.
             */
            public Builder setName(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000002;
                name_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>required string name = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearName() {
                bitField0_ = (bitField0_ & ~0x00000002);
                name_ = getDefaultInstance().getName();
                onChanged();
                return this;
            }
            /**
             * <code>required string name = 2;</code>
             * @param value The bytes for name to set.
             * @return This builder for chaining.
             */
            public Builder setNameBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000002;
                name_ = value;
                onChanged();
                return this;
            }

            private int gender_ ;
            /**
             * <code>required int32 gender = 3;</code>
             * @return Whether the gender field is set.
             */
            @Override
            public boolean hasGender() {
                return ((bitField0_ & 0x00000004) != 0);
            }
            /**
             * <code>required int32 gender = 3;</code>
             * @return The gender.
             */
            @Override
            public int getGender() {
                return gender_;
            }
            /**
             * <code>required int32 gender = 3;</code>
             * @param value The gender to set.
             * @return This builder for chaining.
             */
            public Builder setGender(int value) {
                bitField0_ |= 0x00000004;
                gender_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>required int32 gender = 3;</code>
             * @return This builder for chaining.
             */
            public Builder clearGender() {
                bitField0_ = (bitField0_ & ~0x00000004);
                gender_ = 0;
                onChanged();
                return this;
            }

            private Object phone_ = "";
            /**
             * <code>optional string phone = 4;</code>
             * @return Whether the phone field is set.
             */
            @Override
            public boolean hasPhone() {
                return ((bitField0_ & 0x00000008) != 0);
            }
            /**
             * <code>optional string phone = 4;</code>
             * @return The phone.
             */
            @Override
            public String getPhone() {
                Object ref = phone_;
                if (!(ref instanceof String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        phone_ = s;
                    }
                    return s;
                } else {
                    return (String) ref;
                }
            }
            /**
             * <code>optional string phone = 4;</code>
             * @return The bytes for phone.
             */
            @Override
            public com.google.protobuf.ByteString
            getPhoneBytes() {
                Object ref = phone_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (String) ref);
                    phone_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>optional string phone = 4;</code>
             * @param value The phone to set.
             * @return This builder for chaining.
             */
            public Builder setPhone(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000008;
                phone_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>optional string phone = 4;</code>
             * @return This builder for chaining.
             */
            public Builder clearPhone() {
                bitField0_ = (bitField0_ & ~0x00000008);
                phone_ = getDefaultInstance().getPhone();
                onChanged();
                return this;
            }
            /**
             * <code>optional string phone = 4;</code>
             * @param value The bytes for phone to set.
             * @return This builder for chaining.
             */
            public Builder setPhoneBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000008;
                phone_ = value;
                onChanged();
                return this;
            }
            @Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:kafka.User)
        }

        // @@protoc_insertion_point(class_scope:kafka.User)
        private static final User DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new User();
        }

        public static User getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @Deprecated public static final com.google.protobuf.Parser<User>
                PARSER = new com.google.protobuf.AbstractParser<User>() {
            @Override
            public User parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new User(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<User> parser() {
            return PARSER;
        }

        @Override
        public com.google.protobuf.Parser<User> getParserForType() {
            return PARSER;
        }

        @Override
        public User getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_kafka_User_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_kafka_User_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }
    private static  com.google.protobuf.Descriptors.FileDescriptor
            descriptor;
    static {
        String[] descriptorData = {
                "\n\nUser.proto\022\005kafka\"?\n\004User\022\n\n\002id\030\001 \002(\003\022" +
                        "\014\n\004name\030\002 \002(\t\022\016\n\006gender\030\003 \002(\005\022\r\n\005phone\030\004" +
                        " \001(\tB$\n\027com.qingshan.serializerB\tUserPro" +
                        "to"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {
                        });
        internal_static_kafka_User_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_kafka_User_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_kafka_User_descriptor,
                new String[] { "Id", "Name", "Gender", "Phone", });
    }

    // @@protoc_insertion_point(outer_class_scope)
}
