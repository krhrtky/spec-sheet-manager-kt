<template>
  <v-layout column wrap>
    <v-flex>
      <v-card>
        <v-card-title primary-title>
          Sign On
        </v-card-title>
        <v-form>
          <v-text-field
            label="User Name"
            required
          />
          <v-text-field
            label="E-mail"
            required
          />
          <v-text-field
            label="Password"
            :type="'password'"
            required
          />
          <v-text-field
            label="Password Confirmation"
            :type="'password'"
            required
          />
          <v-btn color="info">Confirm</v-btn>
          <v-btn color="warning">Cancel</v-btn>
        </v-form>
      </v-card>
    </v-flex>
  </v-layout>

</template>

<script>
  import {Component, Vue} from "vue-property-decorator";

  @Component({
    name: 'CreateUser'
  })
  export default class CreateUser extends Vue {
    }
</script>

<style scoped>

</style>
