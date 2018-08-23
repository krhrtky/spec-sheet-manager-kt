import { configure } from "@storybook/vue";
import Vue from "vue";
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.min.css';

Vue.use(Vuetify, {});

const req = require.context("../src", true, /.story.ts$/);
function loadStories() {
    req.keys().forEach(filename => req(filename));
}

configure(loadStories, module);
