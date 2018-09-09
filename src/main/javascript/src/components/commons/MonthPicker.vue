<template>
  <v-menu
    ref="menu"
    :close-on-content-click="false"
    v-model="menu"
    :nudge-right="40"
    :return-value.sync="date"
    lazy
    transition="scale-transition"
    offset-y
    full-width
    max-width="290px"
    min-width="290px"
  >
    <v-text-field
      slot="activator"
      v-model="date"
      :label="title"
      prepend-icon="event"
      readonly
      require
    ></v-text-field>
    <v-date-picker
      v-model="date"
      type="month"
      no-title
      scrollable
    >
      <v-spacer></v-spacer>
      <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
      <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
    </v-date-picker>
  </v-menu>
</template>

<script>
  import {Component, Vue} from "vue-property-decorator";

  @Component({
    name: "MonthPicker",
    props: {
      title: {
        type: String,
        default: 'date',
        require: true
      }
    }
  })
  export default class MonthPicker extends Vue{
    data() {
      return {
        date: null,
        menu: false,
        modal: false
      }
    }

  }
</script>

<style scoped>
  .v-menu__content > .v-card {
    margin: initial;
    padding: initial;
  }


</style>
