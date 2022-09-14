package com.backend.domain.image.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class LocalImageUploader implements ImageUploader{}