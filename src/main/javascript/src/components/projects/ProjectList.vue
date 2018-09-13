<template>
    <v-card>
      <v-card-title primary-title>
        <h1>Project List</h1>
      </v-card-title>
      <v-text-field
        append-icon="search"
        placeholder="Keyword"
      />

      <v-list two-line>
        <template
          v-for="(project, i) in projectList"
        >
          <v-list-tile
            :key="i"
            ripple
            @click=""
          >
            <v-list-tile-content>
              <v-list-tile-title>{{ project.name }}</v-list-tile-title>
              <v-list-tile-sub-title class="text--primary">{{ project.languages }}</v-list-tile-sub-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-list-tile-action-text>FROM: {{project.startDate}}</v-list-tile-action-text>
              <v-list-tile-action-text>TO: {{project.endDate}}</v-list-tile-action-text>
            </v-list-tile-action>

          </v-list-tile>
          <v-divider/>
        </template>
      </v-list>
    </v-card>
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
            languages: 'java',
            startDate: '2018-01',
            endDate: '2018-04',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample',
            languages: 'java',
            startDate: '2018-01',
            endDate: '2018-04',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
          {
            name: 'sample1',
            languages: 'kotlin',
            startDate: '2018-05',
            endDate: '2018-07',
          },
        ]
      }
    }
    select() {
      console.log('click fired.')
    }
  }
</script>

<style scoped>
  .v-card {
    height: auto;
  }

  .v-list {
    overflow: auto;
    max-height: 500px;
  }

</style>
