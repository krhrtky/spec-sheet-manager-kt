<template>
    <v-form>
        <v-container grid-list-xl text-xs-center>
            <v-layout column wrap>
                <v-flex xs10 offset-xs1>
                    <v-text-field
                            label="E-mail"
                            required
                    />
                    <v-text-field
                            label="Password"
                            :type="'password'"
                            required
                    />
                </v-flex>
            </v-layout>
        </v-container>
    </v-form>
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
