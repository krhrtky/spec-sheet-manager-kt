<template>
  <v-list two-line>
    <v-list-tile
      value="true"
      v-for="(project, i) in projectList"
      :key="i"
    >
      <div>{{project.name}}</div>
    </v-list-tile>
  </v-list>
</template>

<script>
  import {Component, Vue} from 'vue-property-decorator';


  @Component({
        name: "ProjectList"
    })
  export default class ProjectList extends Vue {
    data() {
      return {
        projectList: [
          {
            name: 'sample',
          },
          {
            name: 'sample1',
          },
        ]
      }
    }

  }
</script>

<style scoped>

</style>
