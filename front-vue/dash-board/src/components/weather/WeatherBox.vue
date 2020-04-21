<template>
    <div>
        <div>
            {{weather}}
        </div>
        <div>
            weather Box
        </div>
    </div>
</template>

<script>
export default {
    name : 'WaetherBox',
    props : ['weather']
}
</script>