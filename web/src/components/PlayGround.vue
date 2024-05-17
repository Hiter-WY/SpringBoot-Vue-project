<template>
    <div class="playground">
        <GameMap />
    </div>
</template>

<script>

import GameMap from './GameMap';

export default{
    components:{
        GameMap, 
    }
}
</script>

<style>
div.playground{
    width: 40vw;
    height: 40vw;
    margin: 40px auto;
}
</style>