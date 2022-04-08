/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.1
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.toggle.pjsip.pjsua2;

public class Account {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected Account(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Account obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_Account(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    pjsua2JNI.Account_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    pjsua2JNI.Account_change_ownership(this, swigCPtr, true);
  }

  public Account() {
    this(pjsua2JNI.new_Account(), true);
    pjsua2JNI.Account_director_connect(this, swigCPtr, true, true);
  }

  public void create(AccountConfig cfg, boolean make_default) throws Exception {
    pjsua2JNI.Account_create__SWIG_0(swigCPtr, this, AccountConfig.getCPtr(cfg), cfg, make_default);
  }

  public void create(AccountConfig cfg) throws Exception {
    pjsua2JNI.Account_create__SWIG_1(swigCPtr, this, AccountConfig.getCPtr(cfg), cfg);
  }

  public void shutdown() {
    pjsua2JNI.Account_shutdown(swigCPtr, this);
  }

  public void modify(AccountConfig cfg) throws Exception {
    pjsua2JNI.Account_modify(swigCPtr, this, AccountConfig.getCPtr(cfg), cfg);
  }

  public boolean isValid() {
    return pjsua2JNI.Account_isValid(swigCPtr, this);
  }

  public void setDefault() throws Exception {
    pjsua2JNI.Account_setDefault(swigCPtr, this);
  }

  public boolean isDefault() {
    return pjsua2JNI.Account_isDefault(swigCPtr, this);
  }

  public int getId() {
    return pjsua2JNI.Account_getId(swigCPtr, this);
  }

  public static Account lookup(int acc_id) {
    long cPtr = pjsua2JNI.Account_lookup(acc_id);
    return (cPtr == 0) ? null : new Account(cPtr, false);
  }

  public AccountInfo getInfo() throws Exception {
    return new AccountInfo(pjsua2JNI.Account_getInfo(swigCPtr, this), true);
  }

  public void setRegistration(boolean renew) throws Exception {
    pjsua2JNI.Account_setRegistration(swigCPtr, this, renew);
  }

  public void setOnlineStatus(PresenceStatus pres_st) throws Exception {
    pjsua2JNI.Account_setOnlineStatus(swigCPtr, this, PresenceStatus.getCPtr(pres_st), pres_st);
  }

  public void setTransport(int tp_id) throws Exception {
    pjsua2JNI.Account_setTransport(swigCPtr, this, tp_id);
  }

  public void presNotify(PresNotifyParam prm) throws Exception {
    pjsua2JNI.Account_presNotify(swigCPtr, this, PresNotifyParam.getCPtr(prm), prm);
  }

  public BuddyVector2 enumBuddies2() throws Exception {
    return new BuddyVector2(pjsua2JNI.Account_enumBuddies2(swigCPtr, this), true);
  }

  public Buddy findBuddy2(String uri) throws Exception {
    return new Buddy(pjsua2JNI.Account_findBuddy2(swigCPtr, this, uri), true);
  }

  public boolean isZrtpUse() {
    return pjsua2JNI.Account_isZrtpUse(swigCPtr, this);
  }

  public void setZrtpUse(boolean isUse) {
    pjsua2JNI.Account_setZrtpUse(swigCPtr, this, isUse);
  }

  public String getZidFileName() {
    return pjsua2JNI.Account_getZidFileName(swigCPtr, this);
  }

  public void setZidFileName(String fileName) {
    pjsua2JNI.Account_setZidFileName(swigCPtr, this, fileName);
  }

  public void onIncomingCall(OnIncomingCallParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onIncomingCall(swigCPtr, this, OnIncomingCallParam.getCPtr(prm), prm); else pjsua2JNI.Account_onIncomingCallSwigExplicitAccount(swigCPtr, this, OnIncomingCallParam.getCPtr(prm), prm);
  }

  public void onRegStarted(OnRegStartedParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onRegStarted(swigCPtr, this, OnRegStartedParam.getCPtr(prm), prm); else pjsua2JNI.Account_onRegStartedSwigExplicitAccount(swigCPtr, this, OnRegStartedParam.getCPtr(prm), prm);
  }

  public void onRegState(OnRegStateParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onRegState(swigCPtr, this, OnRegStateParam.getCPtr(prm), prm); else pjsua2JNI.Account_onRegStateSwigExplicitAccount(swigCPtr, this, OnRegStateParam.getCPtr(prm), prm);
  }

  public void onIncomingSubscribe(OnIncomingSubscribeParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onIncomingSubscribe(swigCPtr, this, OnIncomingSubscribeParam.getCPtr(prm), prm); else pjsua2JNI.Account_onIncomingSubscribeSwigExplicitAccount(swigCPtr, this, OnIncomingSubscribeParam.getCPtr(prm), prm);
  }

  public void onInstantMessage(OnInstantMessageParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onInstantMessage(swigCPtr, this, OnInstantMessageParam.getCPtr(prm), prm); else pjsua2JNI.Account_onInstantMessageSwigExplicitAccount(swigCPtr, this, OnInstantMessageParam.getCPtr(prm), prm);
  }

  public void onInstantMessageStatus(OnInstantMessageStatusParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onInstantMessageStatus(swigCPtr, this, OnInstantMessageStatusParam.getCPtr(prm), prm); else pjsua2JNI.Account_onInstantMessageStatusSwigExplicitAccount(swigCPtr, this, OnInstantMessageStatusParam.getCPtr(prm), prm);
  }

  public void onTypingIndication(OnTypingIndicationParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onTypingIndication(swigCPtr, this, OnTypingIndicationParam.getCPtr(prm), prm); else pjsua2JNI.Account_onTypingIndicationSwigExplicitAccount(swigCPtr, this, OnTypingIndicationParam.getCPtr(prm), prm);
  }

  public void onMwiInfo(OnMwiInfoParam prm) {
    if (getClass() == Account.class) pjsua2JNI.Account_onMwiInfo(swigCPtr, this, OnMwiInfoParam.getCPtr(prm), prm); else pjsua2JNI.Account_onMwiInfoSwigExplicitAccount(swigCPtr, this, OnMwiInfoParam.getCPtr(prm), prm);
  }

}