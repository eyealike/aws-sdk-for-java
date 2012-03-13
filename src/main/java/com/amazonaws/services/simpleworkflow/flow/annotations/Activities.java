/*
 * Copyright 2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.simpleworkflow.flow.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.JsonDataConverter;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;

/**
 * @Activities annotation is allowed on interfaces to define a set of activities.
 * This interface forms the contract between the implementation of the activities and 
 * the clients used to invoke them.  The client-side is auto-generated by AWS Flow Framework 
 * annotation processor from the interfaces marked with @Activities annotation.
 * 
 * Each method on the interface annotated with @Activities annotation corresponds to 
 * an activity.  Activity methods are not allowed to have {@link Promise} parameters
 * or return types.
 * 
 * @author fateev, samar
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Activities {

    /**
     * Prefix to use for each activity defined within the interface annotated 
     * with @Activities annotation.  Default is empty string which means that 
     * name of interface should be used as the prefix for each activity name.
     */
    String activityNamePrefix() default "";

    /**
     * Version to use to each activity defined within the interface annotated 
     * with @Activities annotation.  Default is empty string which means that
     * version should be specified using {@link Activity#version()} on every 
     * method individually.  Alternatively you can specify the version for 
     * all activities defined within the interface using this attribute.
     * 
     * AWS Flow Framework annotation processor will report an error if version 
     * for an activity is not specified through {@link Activities#version()} or 
     * {@link Activity#version()}.
     */
    String version() default "";

    /**
     * This is used to specify {@link DataConverter} type to use for 
     * serialization/de-serialization of activity method parameters and return types.
     * 
     * Default is {@link NullDataConverter} which means to use the default 
     * DataConverter used by framework.  Default DataConverter used by framework is 
     * {@link JsonDataConverter}.
     */
    Class<? extends DataConverter> dataConverter() default NullDataConverter.class;
}
