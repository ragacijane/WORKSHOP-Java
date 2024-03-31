# Energy System
> The project was created while studying Java threads and the java.awt library. More details can be found in the PDF file.
- Energy System is Java application that runs Energy System with folowing classes.
- Parcel is Label (from java.awt). Parcel is made with one letter mark colored white and it can be selected.
- Water Surface is Parcel colored CYAN marked with W
- Grass Surface is Parcel colored GREEN marked with G
- Batery has capacity and it is full with creating. You can add energy to battery or empty it, also capacity can be checked.
- Producer is active Parcel (implements Runnable). It is created with battery, and by the time it produce energy and add it to battery. When energy is produced it's letter mark is colored RED.
- HydroPlant is producer colored BLUE and marked with H. HydroPlant can produce energy only if it is surrounded by at least one Water Surface.
- Property is grid of Parcels created with 70% Grass Surface and 30% Water Surface. Selected Parcel's mark is zoomed by 10%

https://github.com/ragacijane/Classes-and-Threads-Java/assets/74156547/e76b5cb1-87b2-4f8f-b953-d55433144062
