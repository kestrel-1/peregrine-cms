<!--
  #%L
  admin base - UI Apps
  %%
  Copyright (C) 2017 headwire inc.
  %%
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at
  
  http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
  #L%
  -->
<template>
  <div>
    <div class="palette-stylsheet-wrapper">
      <link rel="stylesheet"
            :href="`/etc/felibs/${this.templatePath}/css/colors.css`"
            type="text/css"/>
      <link rel="stylesheet"
            :href="`/content/${this.templatePath}/pages/css/commons.css`"
            type="text/css"/>
      <link rel="stylesheet"
            :href="selectedPath"
            type="text/css"/>
    </div>
    <div class="row">
      <div class="col s12 m4 l4">
        <label>Select Color Palette</label>
        <ul class="collection">
          <li class="collection-item"
              v-for="palette in palettes"
              v-bind:key="palette.name"
              :class="{active: selectedPath === palette.path}"
              @click.stop.prevent="onSelect(palette)">
            {{ palette.name }}
          </li>
        </ul>
      </div>
      <div class="col s12 m8 l8">
        <div class="palette-previews" id="peregrine-app">
          <template v-for="theme in themes">
            <label class="theme-name" :key="theme">{{ theme }}</label>
            <div class="theme-preview" :key="`theme-${theme}`" :class="`theme-${theme}`">
              <div v-for="(color, idx) in colors"
                  :key="idx"
                  :style="{background: `var(--${color.var})`, color: color.text}"
                  :title="color.label"
                  class="palette-col">
                {{ color.label }}
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    props: {
      templatePath: {
        type: String,
        required: true
      },
      palettes: {
        type: Array,
        required: true
      }
    },
    data() {
      return {
        selectedPath: null,
        themes: ['light', 'dark', 'none'],
        colors: [
          {
            label: 'primary background color',
            var: 'bg-primary',
            text: 'var(--text-primary-color)'
          },
          {
            label: 'secondary background color',
            var: 'bg-secondary',
            text: 'var(--text-secondary-color)'
          },
          {
            label: 'primary text color',
            var: 'text-primary-color',
            text: 'var(--bg-primary)'
          },
          {
            label: 'secondary text color',
            var: 'text-secondary-color',
            text: 'var(--bg-secondary)'
          },
          {
            label: 'primary border color',
            var: 'border-primary-color',
            text: 'var(--text-primary-color)'
          }
        ]
      }
    },
    mounted() {
      if (this.palettes && this.palettes.length > 0) {
        this.onSelect(this.palettes[0])
      }
    },
    methods: {
      onSelect(palette) {
        this.selectedPath = palette.path
        this.$emit('select', this.selectedPath)
      }
    }
  }
</script>

<style scoped>
  .palette-previews .theme-name {
    /* text-transform: uppercase; */
    text-align: center;
  }

  .palette-previews .theme-preview {
    display: flex;
    border: 1px solid #000000;
    border-left-width: 0;
    /* height: 150px; */
    text-align: center;
  }

  .palette-previews .theme-preview > .palette-col {
    border-left: 1px solid #000000;
    display: flex;
    flex: 1;
    justify-content: center;
    align-content: center;
    flex-direction: column;
    padding: 1rem;
  }
</style>
