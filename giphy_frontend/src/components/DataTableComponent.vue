<template>
  <v-data-table
    :headers="headers"
    :items="giphys"
    sort-by="id"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
        flat
      >
        <v-toolbar-title>Giphy Trending</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Are you sure you want to delete this item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template #[`item.embed_url`]="{ item }">
        <a target="_blank" :href="`${item.embed_url}`">
            {{ item.embed_url}}
        </a>
  </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
  </v-data-table>
</template>

<script>
import GiphyService from '../service/GiphyService'
  export default {
    data: () => ({
      dialog: false,
      dialogDelete: false,
      headers: [
        { text: 'ID', value: 'id' },
        { text: 'Giphy ID', value: 'giphy_id' },
        { text: 'Title', value: 'title' },
        { text: 'DateTime When Trending', value: 'trending_datetime' },
        { text: 'Giphy URL', value: 'embed_url', sortable: false },
        { text: 'Actions', value: "actions", sortable: false },
      ],
      giphys: [],
    }),

    watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

    created () {
        this.giphyService = new GiphyService();
        this.giphyService.getGiphys().then(data => this.giphys = data)
    },

    methods: {
        deleteItem (item) {
            this.editedIndex = this.giphys.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialogDelete = true
        },

        deleteItemConfirm () {
            this.giphyService.deleteGiphy(this.editedItem.id)
            this.giphys.splice(this.editedIndex, 1)
            this.closeDelete()
        },

        close () {
            this.dialog = false
            this.$nextTick(() => {
            this.editedItem = Object.assign({}, this.defaultItem)
            this.editedIndex = -1
            })
        },

        closeDelete () {
            this.dialogDelete = false
            this.$nextTick(() => {
            this.editedItem = Object.assign({}, this.defaultItem)
            this.editedIndex = -1
            })
        },
    },
  }
</script>