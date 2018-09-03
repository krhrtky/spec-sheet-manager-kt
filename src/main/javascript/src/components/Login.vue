<template>
    <v-container>
        <v-layout column wrap>
            <v-flex>
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
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component({
    name: "Login"
})
export default class Login extends Vue {}
</script>

<style scoped>

</style>
