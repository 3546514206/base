import Vue from 'vue'
import Vuex from 'vuex'
import idb from '@/api/idb';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    sounds:[]
  },
  mutations: {
  },
  actions: {
    async deleteSound(context, sound) {
      console.log('store is being asked to delete '+sound.id);
      await idb.deleteSound(sound); 
    },
    async loadSounds(context) {
      console.log('loadSounds');
      context.state.sounds = [];
      let sounds = await idb.getSounds();
      sounds.forEach(s => {
        context.state.sounds.push(s);
      });
    },
    async saveSound(context, sound) {
      await idb.saveSound(sound);
    }
  },
  modules: {
  }
})
