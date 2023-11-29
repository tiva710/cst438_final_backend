package com.cst438.domain;

import java.util.stream.Collectors;

public record TrackDTO(String id, String name, String artistNames, String albumName) {
}
