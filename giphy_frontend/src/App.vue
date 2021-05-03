<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <v-spacer></v-spacer>
    </v-app-bar>

    <v-main>
      <data-table-component></data-table-component>
    </v-main>
  </v-app>
</template>

<script>
import DataTableComponent from './components/DataTableComponent.vue'
export default {
  name: 'App',
  components: {
    DataTableComponent
  }
};
</script>
