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
 * Class {@link DTTimeInfo} corresponds to an element of type Time in a DTDL model.
 */
public class DTTimeInfo extends DTTemporalSchemaInfo implements TypeChecker, Equatable<DTTimeInfo> {
    private static final Map<Integer, String> BAD_TYPE_ACTION_FORMAT = new HashMap<>();

    private static final Map<Integer, String> BAD_TYPE_CAUSE_FORMAT = new HashMap<>();

    private static final Map<Integer, Set<DTEntityKind>> CONCRETE_KINDS = new HashMap<>();

    private static final HashSet<String> VERSION_LESS_TYPES = new HashSet<>();

    static {
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Entity");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:PrimitiveSchema");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Schema");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:TemporalSchema");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Time");

        CONCRETE_KINDS.put(2, new HashSet<>());
        CONCRETE_KINDS.get(2).add(DTEntityKind.TIME);

        CONCRETE_KINDS.put(3, new HashSet<>());
        CONCRETE_KINDS.get(3).add(DTEntityKind.TIME);

        BAD_TYPE_ACTION_FORMAT.put(2, "Choose the following value for property '{property}': time.");
        BAD_TYPE_ACTION_FORMAT.put(3, "Choose the following value for property '{property}': time.");

        BAD_TYPE_CAUSE_FORMAT.put(2, "{primaryId:p} property '{property}' has value{secondaryId:e} that is not a standard value for this property.");
        BAD_TYPE_CAUSE_FORMAT.put(3, "{primaryId:p} property '{property}' has value{secondaryId:e} that is not a standard value for this property.");
    }
}
