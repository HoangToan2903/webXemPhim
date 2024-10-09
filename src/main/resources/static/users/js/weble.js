// // javascript:
// (() => {
//     let handler = window.bozdoz_video_loop;
//     /* get first visible video */
//     const videos = document.querySelectorAll("video");
//
//     let video = videos[0];
//
//     if (videos.length > 1) {
//         for (const v of videos) {
//             const {bottom} = v.getBoundingClientRect();
//
//             if (bottom > 0) {
//                 video = v;
//                 break;
//             }
//         }
//     }
//
//     if (!video) {
//         console.log("[bozdoz] no video");
//         return;
//     }
//
//     if (handler) {
//         video.removeEventListener("timeupdate", handler);
//     }
//
//     const start = prompt("Start time:");
//
//     if (!start) {
//         return;
//     }
//
//     const end = prompt("End time:");
//
//     if (!end) {
//         return;
//     }
//
//     const getSeconds = (t) => {
//         if (!t.includes(":")) {
//             return Number(t);
//         }
//
//         const [min, sec] = t.split(":").map(Number);
//
//         return min * 60 + sec;
//     };
//
//     const s = getSeconds(start);
//     const e = getSeconds(end);
//
//     if (Number.isNaN(s) || Number.isNaN(e)) {
//         console.log("[bozdoz]", s, e);
//         return;
//     }
//
//     handler = window.bozdoz_video_loop = () => {
//         if (video.currentTime > e || video.currentTime < s) {
//             video.currentTime = s;
//         }
//     };
//
//     video.addEventListener("timeupdate", handler);
//     video.addEventListener(
//         "click",
//         () => {
//             video.removeEventListener("timeupdate", handler);
//         },
//         {
//             once: true
//         }
//     );
//
//     handler();
//     video.play();
// })();
