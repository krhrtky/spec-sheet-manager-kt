<template>
    <v-card>
        <v-card-title primary-title>
            Login
        </v-card-title>
        <v-container
                fluid
                grid-list-lg
        >
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
    </v-card>
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
        margin: 10rem;
    }

    .flex > .v-card {
        margin: 0;
    }

</style>
