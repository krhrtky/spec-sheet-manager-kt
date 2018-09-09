<template>
  <v-layout column wrap>
    <v-flex>
      <v-card>
        <v-card-title primary-title>
          <h1>New Project</h1>
        </v-card-title>
        <v-form>
          <v-text-field
            label="Project Name"
            required
          />
          <month-picker title="Start Date"/>
          <month-picker title="End Date"/>
          <v-text-field
            label="Languages"
            required
          />
          <v-text-field
            label="FrameWorks"
            required
          />
          <v-text-field
            label="Middlewares"
            required
          />
          <v-textarea
            auto-grow
            label="Description"
            rows="5"
          ></v-textarea>
          <v-btn color="info">Confirm</v-btn>
          <v-btn color="warning">Cancel</v-btn>
        </v-form>
      </v-card>
    </v-flex>
  </v-layout>

</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import MonthPicker from '@/components/commons/MonthPicker.vue';

  @Component({
    name: 'NewProject',
    components: {
      MonthPicker,
    }
  })
  export default class NewProject extends Vue {

  }

</script>

<style scoped>

</style>
