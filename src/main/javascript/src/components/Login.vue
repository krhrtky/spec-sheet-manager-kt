<template>
  <v-layout column wrap>
    <v-flex>
      <v-card>
        <v-card-title primary-title>
          Login
        </v-card-title>
        <v-form>
          <v-text-field
            label="E-mail"
            required
          />
          <v-text-field
            label="Password"
            :type="'password'"
            required
          />
          <v-btn color="info">Login</v-btn>
          <v-btn color="warning">Clear</v-btn>
        </v-form>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script lang="ts">
  import { Component, Vue } from "vue-property-decorator";

  @Component({
    name: "Login"
  })
  export default class Login extends Vue {}
</script>

<style scoped>
  .v-card {
    margin: 0 10rem;
    padding: 0 5rem 5rem 5rem;
  }
</style>
