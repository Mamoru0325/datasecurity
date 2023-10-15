<style scoped>
h4 {
  align-items: first baselines;
  color: rgb(42, 165, 54);
}
h5 {
  font-size: 25px;
  text-align: center;  
  color: rgb(255, 0, 0);
}
p {
  font-size: 18px;
  margin-left: 5%;
}
</style>
<template>
  <div>
    <MyNavBar />
    <br>
    <h1>สอนการเข้ารหัสและถอดรหัส Caesar</h1>
    <div style="text-align: left; margin-left: 15%">
      <h4>Caesar Substitution Ciphers<br></h4>

      <p>• เป็นการแทนค่าแต่ละตัวอักษรด้วยสัญลักษณ์เพียงตัวเดียว เป็นวิธีทีง่ายที่สุด ใช้มาตั้งแต่สมัยจูเลียส ซี<br>
        &ensp;ซาร์ ในการเข้ารหัสเนื้อความจดหมายส่งไปให้ทัพทหารระหว่างการรบ<br>

        • ตัวอย่าง ใช้ความสัมพันธ์ของอักษรในภาษาอังกฤษ 26 ตัว โดยที่ ตัวอักษรใน Cipher Text จะได้<br>
        &ensp;จาก Plain Text + ไปยัง 3 ลำดับของตัวอักษรในภาษาอังกฤษ ในทางกลับกัน Plain Text จะเท่ากับ<br>

        &ensp;Cipher Text - ไปยัง 3 ลำดับของตัวอักษรในภาษาอังกฤษ<br><br></p>

        <h5>Plain Text : a b c d e f g h I j k l m n o p q r s t u v w x y z</h5><br>

        <h5>Cipher Text : d e f g h I j k l m n o p q r s t u v w x y z a b c</h5><br><br>

        <p>&ensp;เช่น Love You --- 0ryh brx<br>

        วิธีนี้เมื่อมีการขโมย Cipher Text สามารถถอดได้ไม่ยากนัก เนื่องจากมีคำตอบที่เป็นไปทั้งหมด 25 <br>

        คำตอบ <br>

        • โดยทดสอบการแทนที่ตัวอักษรไปเรื่อยๆ ก็จะเจอข้อความที่สามารถอ่านได้ <br>

        • การถอดรหัสข้อมูล 0ryh brx <br>

        &ensp;ลำดับตัวอักษร a b c d e f g h I j k l m n o p q r s t u v w x y z <br>

        &ensp;เลื่อนกลับ 1 ตำแหน่ง : nqxq aqw <br>

        &ensp;เลื่อนกลับ 2 ตำแหน่ง : mpwf zpv <br>

        &ensp;เลื่อนกลับ 3 ตำแหน่ง : love you ◊ จะเจอคำที่สามารถอ่านได้</p>
    </div>
    <br>

    <MyButtonBar />
  </div>
</template>
  
<script>
// @ is an alias to /src
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
export default {
  name: 'CizarView',
  components: {
    MyNavBar,
    MyButtonBar
  }
}
</script>
  