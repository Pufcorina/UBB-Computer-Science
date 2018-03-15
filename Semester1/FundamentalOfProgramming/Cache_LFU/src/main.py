import traceback

from Cache_LFU.src.controller.student_controller import Student_controller
from Cache_LFU.src.domain.entity import Student
from Cache_LFU.src.repository.file_repository import FileRepository
from Cache_LFU.src.ui.console import Console
from Cache_LFU.src.ui.gui import Ui_Form
from Cache_LFU.src.util.lfu_cache import LFU_Cache

if __name__ == "__main__":
    try:
        cache = LFU_Cache()
        student_repository = FileRepository(cache, "..\data\students", Student)

        student_controller = Student_controller(student_repository)

        #console = Console(student_controller)
        #console.runApplication()

        console = Ui_Form(student_controller)
        console.runGui()

    except Exception as ex:
        traceback.print_exc()
        print(ex)