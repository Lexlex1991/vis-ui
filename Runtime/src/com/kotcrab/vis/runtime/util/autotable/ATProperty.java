/*
 * Copyright 2014-2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotcrab.vis.runtime.util.autotable;

import com.artemis.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used on fields inside {@link Component}s to make them editable inside VisEditor. Target component
 * class must be registered using AutoComponentTable. See VisEditor source for more details.
 * <p>
 * This annotation supports float, int and boolean fields. It can be also used to invoke getters and setters for those
 * primitive types on other objects types. See {@link #setStrategy()}
 * Auto table will automatically create number input field or checkbox and update it when needed.
 * @author Kotcrab
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ATProperty {
	/** @return human friendly field name that will be used in VisEditor UI. */
	String fieldName () default "";

	/** @return field max value, if target field is boolean this if ignored */
	float max () default Float.MAX_VALUE;

	/** @return field min value, if target field is boolean this if ignored */
	float min () default Float.MIN_VALUE;

	/**
	 * @return filed set strategy that will be used for this annotation, if this is set to {@link FieldSetStrategy#DIRECT_CHANGE}
	 * this field will be tried to set directly. If this is set to {@link FieldSetStrategy#GETTER_AND_SETTER} an getter
	 * and setter will be used to change this object, in this mode {@link #targetType()}, {@link #getterName()} and {@link #setterName()} must be set.
	 */
	FieldSetStrategy setStrategy () default FieldSetStrategy.DIRECT_CHANGE;

	/** @return type that is used by getter and setter, used only if {@link #setStrategy()} is set to {@link FieldSetStrategy#GETTER_AND_SETTER} */
	Class<?> targetType () default Object.class;

	/** @return getter name used to get value from this object, used only if {@link #setStrategy()} is set to {@link FieldSetStrategy#GETTER_AND_SETTER} */
	String getterName () default "";

	/** @return setter name used to set value for this object, used only if {@link #setStrategy()} is set to {@link FieldSetStrategy#GETTER_AND_SETTER} */
	String setterName () default "";
}

