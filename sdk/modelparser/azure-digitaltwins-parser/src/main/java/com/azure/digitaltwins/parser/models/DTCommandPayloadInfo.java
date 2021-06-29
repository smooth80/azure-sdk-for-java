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
 * Class {@link DTCommandPayloadInfo} corresponds to an element of type CommandPayload in a DTDL model.
 */
public class DTCommandPayloadInfo extends DTSchemaFieldInfo implements TypeChecker, PropertyValueConstrainer, PropertyInstanceBinder, Equatable<DTCommandPayloadInfo> {
    private static final Map<Integer, String> BAD_TYPE_ACTION_FORMAT = new HashMap<>();

    private static final Map<Integer, String> BAD_TYPE_CAUSE_FORMAT = new HashMap<>();

    private static final Map<Integer, Set<DTEntityKind>> CONCRETE_KINDS = new HashMap<>();

    private static final HashSet<String> VERSION_LESS_TYPES = new HashSet<>();

    private Set<Integer> schemaAllowedVersionsV2 = new HashSet<>(Arrays.asList(2));

    private Set<Integer> schemaAllowedVersionsV3 = new HashSet<>(Arrays.asList(3, 2));

    private List<String> schemaInstanceProperties = null;

    private List<ValueConstraints> schemaValueConstraints = null;

    static {
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:CommandPayload");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:Entity");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:NamedEntity");
        VERSION_LESS_TYPES.add("dtmi:dtdl:class:SchemaField");

        CONCRETE_KINDS.put(2, new HashSet<>());
        CONCRETE_KINDS.get(2).add(DTEntityKind.COMMAND_PAYLOAD);

        CONCRETE_KINDS.put(3, new HashSet<>());
        CONCRETE_KINDS.get(3).add(DTEntityKind.COMMAND_REQUEST);
        CONCRETE_KINDS.get(3).add(DTEntityKind.COMMAND_RESPONSE);

        BAD_TYPE_ACTION_FORMAT.put(2, "Provide a value for property '{property}' with @type CommandPayload.");
        BAD_TYPE_ACTION_FORMAT.put(3, "Provide a value for property '{property}' with @type CommandRequest or CommandResponse.");

        BAD_TYPE_CAUSE_FORMAT.put(2, "{primaryId:p} property '{property}' has value{secondaryId:e} that does not have @type of CommandPayload.");
        BAD_TYPE_CAUSE_FORMAT.put(3, "{primaryId:p} property '{property}' has value{secondaryId:e} that does not have @type of CommandRequest or CommandResponse.");
    }
}
