// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) Code Generator

package com.azure.digitaltwins.parser.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class {@link DTRelationshipInfo} corresponds to an element of type Relationship in a DTDL model.
 */
public class DTRelationshipInfo extends DTContentInfo implements TypeChecker, PropertyValueConstrainer, PropertyInstanceBinder, Equatable<DTRelationshipInfo> {
    private static final Map<Integer, String> BAD_TYPE_ACTION_FORMAT = new HashMap<>();

    private static final Map<Integer, String> BAD_TYPE_CAUSE_FORMAT = new HashMap<>();

    private static final Map<Integer, Set<DTEntityKind>> CONCRETE_KINDS = new HashMap<>();

    /**
     * Regular expression pattern for values of property 'target' for DTDLv2.
     */
    protected static final Pattern TARGET_PROPERTY_REGEX_PATTERN_V2 = Pattern.compile("^dtmi:[A-Za-z](?:[A-Za-z0-9_]*[A-Za-z0-9])?(?::[A-Za-z](?:[A-Za-z0-9_]*[A-Za-z0-9])?)*;[1-9][0-9]{0,8}$");

    /**
     * Regular expression pattern for values of property 'target' for DTDLv3.
     */
    protected static final Pattern TARGET_PROPERTY_REGEX_PATTERN_V3 = Pattern.compile("^dtmi:[A-Za-z](?:[A-Za-z0-9_]*[A-Za-z0-9])?(?::[A-Za-z](?:[A-Za-z0-9_]*[A-Za-z0-9])?)*(?:;[1-9][0-9]{0,8}(?:\.[1-9][0-9]{0,5})?)?$");

    private static final HashSet<String> VERSION_LESS_TYPES = new HashSet<>();

    private Integer maxMultiplicity;

    private Integer minMultiplicity;

    private List<DTPropertyInfo> properties;

    private Set<Integer> propertiesAllowedVersionsV2 = new HashSet<>(Arrays.asList(2));

    private Set<Integer> propertiesAllowedVersionsV3 = new HashSet<>(Arrays.asList(3, 2));

    private List<String> propertiesInstanceProperties = null;

    private List<ValueConstraints> propertiesValueConstraints = null;

    private Dtmi target;

    private Boolean writable;

    static {
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Content");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Entity");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:NamedEntity");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Relationship");

        CONCRETE_KINDS.put(2, new HashSet<>());
        CONCRETE_KINDS.get(2).add(DTEntityKind.RELATIONSHIP);

        CONCRETE_KINDS.put(3, new HashSet<>());
        CONCRETE_KINDS.get(3).add(DTEntityKind.RELATIONSHIP);

        BAD_TYPE_ACTION_FORMAT.put(2, "Provide a value for property '{property}' with @type Relationship.");
        BAD_TYPE_ACTION_FORMAT.put(3, "Provide a value for property '{property}' with @type Relationship.");

        BAD_TYPE_CAUSE_FORMAT.put(2, "{primaryId:p} property '{property}' has value{secondaryId:e} that does not have @type of Relationship.");
        BAD_TYPE_CAUSE_FORMAT.put(3, "{primaryId:p} property '{property}' has value{secondaryId:e} that does not have @type of Relationship.");
    }

    /**
     * Get The value of the 'maxMultiplicity' property of the DTDL element that corresponds to this object.
     * @return maxMultiplicity.
     */
    public Integer getMaxMultiplicity() {
        return this.maxMultiplicity
    }

    /**
     * Get The value of the 'minMultiplicity' property of the DTDL element that corresponds to this object.
     * @return minMultiplicity.
     */
    public Integer getMinMultiplicity() {
        return this.minMultiplicity
    }

    /**
     * Get The value of the 'properties' property of the DTDL element that corresponds to this object.
     * @return properties.
     */
    public List<DTPropertyInfo> getProperties() {
        return this.properties
    }

    /**
     * Get The value of the 'target' property of the DTDL element that corresponds to this object.
     * @return target.
     */
    public Dtmi getTarget() {
        return this.target
    }

    /**
     * Get The value of the 'writable' property of the DTDL element that corresponds to this object.
     * @return writable.
     */
    public Boolean getWritable() {
        return this.writable
    }

    /**
     * Set The value of the 'maxMultiplicity' property of the DTDL element that corresponds to this object.
     * @param value Property value.
     */
    void setMaxMultiplicity(Integer value) {
        this.maxMultiplicity = value;
    }

    /**
     * Set The value of the 'minMultiplicity' property of the DTDL element that corresponds to this object.
     * @param value Property value.
     */
    void setMinMultiplicity(Integer value) {
        this.minMultiplicity = value;
    }

    /**
     * Set The value of the 'properties' property of the DTDL element that corresponds to this object.
     * @param value Property value.
     */
    void setProperties(List<DTPropertyInfo> value) {
        this.properties = value;
    }

    /**
     * Set The value of the 'target' property of the DTDL element that corresponds to this object.
     * @param value Property value.
     */
    void setTarget(Dtmi value) {
        this.target = value;
    }

    /**
     * Set The value of the 'writable' property of the DTDL element that corresponds to this object.
     * @param value Property value.
     */
    void setWritable(Boolean value) {
        this.writable = value;
    }
}
