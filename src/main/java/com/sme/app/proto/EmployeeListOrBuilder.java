// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sme.proto

package com.sme.app.proto;

public interface EmployeeListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.sme.app.proto.EmployeeList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.sme.app.proto.Employee product = 1;</code>
   */
  java.util.List<com.sme.app.proto.Employee> 
      getProductList();
  /**
   * <code>repeated .com.sme.app.proto.Employee product = 1;</code>
   */
  com.sme.app.proto.Employee getProduct(int index);
  /**
   * <code>repeated .com.sme.app.proto.Employee product = 1;</code>
   */
  int getProductCount();
  /**
   * <code>repeated .com.sme.app.proto.Employee product = 1;</code>
   */
  java.util.List<? extends com.sme.app.proto.EmployeeOrBuilder> 
      getProductOrBuilderList();
  /**
   * <code>repeated .com.sme.app.proto.Employee product = 1;</code>
   */
  com.sme.app.proto.EmployeeOrBuilder getProductOrBuilder(
      int index);
}