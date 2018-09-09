import Vue from 'vue'
import {
  transitions,
  VApp,
  VBtn,
  VCard,
  VDatePicker,
  VFooter,
  VForm,
  VGrid,
  VIcon,
  VList,
  VMenu,
  VNavigationDrawer,
  VTextarea,
  VTextField,
  VToolbar,
  Vuetify,
} from 'vuetify'
import 'vuetify/src/stylus/app.styl'

Vue.use(Vuetify, {
  components: {
    VApp,
    VNavigationDrawer,
    VFooter,
    VList,
    VBtn,
    VIcon,
    VGrid,
    VToolbar,
    transitions,
    VForm,
    VTextField,
    VCard,
    VMenu,
    VDatePicker,
    VTextarea,
  },
})
